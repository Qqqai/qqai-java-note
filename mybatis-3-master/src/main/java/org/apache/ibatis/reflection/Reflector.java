/**
 * Copyright 2009-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ibatis.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.ReflectPermission;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.reflection.invoker.AmbiguousMethodInvoker;
import org.apache.ibatis.reflection.invoker.GetFieldInvoker;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.invoker.MethodInvoker;
import org.apache.ibatis.reflection.invoker.SetFieldInvoker;
import org.apache.ibatis.reflection.property.PropertyNamer;

/**
 * This class represents a cached set of class definition information that allows for easy mapping between property names and getter/setter methods.
 *
 * @author Clinton Begin
 */
public class Reflector {

  private final Class<?> type;
  private final String[] readablePropertyNames;
  private final String[] writablePropertyNames;
  private final Map<String, Invoker> setMethods = new HashMap<>();
  private final Map<String, Invoker> getMethods = new HashMap<>();
  private final Map<String, Class<?>> setTypes = new HashMap<>();
  private final Map<String, Class<?>> getTypes = new HashMap<>();
  private Constructor<?> defaultConstructor;

  private Map<String, String> caseInsensitivePropertyMap = new HashMap<>();

  /**
   * 构造器
   *
   * @param clazz 如果是 reflectorMap.computeIfAbsent(type, Reflector::new)  那么传进来的clazz是type类型的 详情看ConcurrentMap::computeIfAbsent(K key, Function<?
   *              super K, ? extends V> mappingFunction)
   */
  public Reflector(Class<?> clazz) {
    type = clazz;
    addDefaultConstructor(clazz);
    addGetMethods(clazz);
    addSetMethods(clazz);
    addFields(clazz);
    readablePropertyNames = getMethods.keySet().toArray(new String[0]);
    writablePropertyNames = setMethods.keySet().toArray(new String[0]);
    for (String propName : readablePropertyNames) {
      caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
    }
    for (String propName : writablePropertyNames) {
      caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
    }
  }

  /**
   * 根据clazz的反射对象创建一个 FIXME 有参的构造器
   *
   * @param clazz
   */
  private void addDefaultConstructor(Class<?> clazz) {
    Constructor<?>[] constructors = clazz.getDeclaredConstructors();
    // 过滤无参
    Arrays.stream(constructors).filter(constructor -> constructor.getParameterTypes().length == 0)
        // 赋值 如果存在
        .findAny().ifPresent(constructor -> this.defaultConstructor = constructor);
  }

  /**
   * 获取clazz所哟的Get类型的方法
   *
   * @param clazz
   */
  private void addGetMethods(Class<?> clazz) {
    Map<String, List<Method>> conflictingGetters = new HashMap<>();
    // 获取所有的method对象
    Method[] methods = getClassMethods(clazz);
    // 过滤收集
    Arrays.stream(methods).filter(m -> m.getParameterTypes().length == 0 && PropertyNamer.isGetter(m.getName()))
        .forEach(m ->
            // 添加方法和属性的映射关系
            addMethodConflict(
                conflictingGetters,
                PropertyNamer.methodToProperty(m.getName()),
                m
            )
        );
    // 解决Getter方法冲突 并添加代理
    resolveGetterConflicts(conflictingGetters);
  }

  /**
   * 解决Getter方法冲突 并添加代理
   */
  private void resolveGetterConflicts(Map<String, List<Method>> conflictingGetters) {
    // conflictingGetters.entrySet()  属性和对应方法的映射
    for (Entry<String, List<Method>> entry : conflictingGetters.entrySet()) {
      Method winner = null;
      String propName = entry.getKey();
      // 是否存在不明确的问题
      boolean isAmbiguous = false;
      // 无冲突 唯一一个
      for (Method candidate : entry.getValue()) {
        // 无冲突 null
        if (winner == null) {
          // 直接赋值
          winner = candidate;
          // 结束本次循环 无冲突 结束循环
          continue;
        }
        // 有冲突 获取返回值类型
        Class<?> winnerType = winner.getReturnType();
        // 获取返回值类型
        Class<?> candidateType = candidate.getReturnType();
        // 判断是否相同
        if (candidateType.equals(winnerType)) {
          // 是布尔类型
          if (!boolean.class.equals(candidateType)) {
            // isAmbiguous给true
            isAmbiguous = true;
            // 结束循环
            break;
          } else if (candidate.getName().startsWith("is")) {
            // 方法名用is开头也是布尔类型的方法
            winner = candidate;
          }
          // 不同 判断candidateType是否是winnerType的夫类型或是超类型
        } else if (candidateType.isAssignableFrom(winnerType)) {
          // getter类型是后代
          //  判断 winnerType 是否是 candidateType 的夫类型或是超类型
        } else if (winnerType.isAssignableFrom(candidateType)) {
          winner = candidate;
        } else {
          // 类型完全不同  也没有继承关系
          isAmbiguous = true;
          // 跳出
          break;
        }
      }
      // 添加代理
      addGetMethod(propName, winner, isAmbiguous);
    }
  }

  /**
   * 按照一定的方式添加代理
   */
  private void addGetMethod(String name, Method method, boolean isAmbiguous) {
    // 根据 isAmbiguous 判断添方式
    MethodInvoker invoker = isAmbiguous
        // 第一种 会直接抛异常
        ? new AmbiguousMethodInvoker(method, MessageFormat.format(
        "Illegal overloaded getter method with ambiguous type for property ''{0}'' in class ''{1}''. This breaks the JavaBeans specification and can cause unpredictable results.",
        name, method.getDeclaringClass().getName()))
        // 第二种 继承Invoker类 动态代理
        : new MethodInvoker(method);
    // 添加属性名和代理方法映射关系
    getMethods.put(name, invoker);
    // 确定返回值类型
    Type returnType = TypeParameterResolver.resolveReturnType(method, type);
    // 添加属性名和返回值类型的映射关系
    getTypes.put(name, typeToClass(returnType));
  }

  /**
   * 类似于getter方法的添加
   */
  private void addSetMethods(Class<?> clazz) {
    Map<String, List<Method>> conflictingSetters = new HashMap<>();
    Method[] methods = getClassMethods(clazz);
    Arrays.stream(methods).filter(m -> m.getParameterTypes().length == 1 && PropertyNamer.isSetter(m.getName()))
        .forEach(m ->
            addMethodConflict(conflictingSetters, PropertyNamer.methodToProperty(m.getName()), m)
        );
    // 解决方法冲突并添加代理的映射关系
    resolveSetterConflicts(conflictingSetters);
  }

  /**
   * 添加冲突方法
   */
  private void addMethodConflict(Map<String, List<Method>> conflictingMethods, String name, Method method) {
    // 判断是否为属性名
    if (isValidPropertyName(name)) {
      // 添加映射关系
      List<Method> list = conflictingMethods.computeIfAbsent(name, k -> new ArrayList<>());
      list.add(method);
    }
  }

  /**
   * 解决方法冲突并添加代理的映射关系
   */
  private void resolveSetterConflicts(Map<String, List<Method>> conflictingSetters) {
    for (Entry<String, List<Method>> entry : conflictingSetters.entrySet()) {
      // 属性名
      String propName = entry.getKey();
      List<Method> setters = entry.getValue();
      Class<?> getterType = getTypes.get(propName);
      // 是否是异常代理
      boolean isGetterAmbiguous = getMethods.get(propName) instanceof AmbiguousMethodInvoker;
      boolean isSetterAmbiguous = false;
      Method match = null;
      for (Method setter : setters) {
        // 参数类型是否匹配
        if (!isGetterAmbiguous && setter.getParameterTypes()[0].equals(getterType)) {
          // 应该是最好的搭配
          match = setter;
          break;
        }
        // 把setters中所有的setter都和match匹配一遍
        if (!isSetterAmbiguous) {
          // 匹配方法
          match = pickBetterSetter(match, setter, propName);
          isSetterAmbiguous = match == null;
        }
      }
      // 添加setter映射关系
      if (match != null) {
        // 添加映射
        addSetMethod(propName, match);
      }
    }
  }

  /**
   * 匹配setter方法
   */
  private Method pickBetterSetter(Method setter1, Method setter2, String property) {
    if (setter1 == null) {
      return setter2;
    }
    Class<?> paramType1 = setter1.getParameterTypes()[0];
    Class<?> paramType2 = setter2.getParameterTypes()[0];
    // 返回值类型 paramType1是否是paramType2的超类
    if (paramType1.isAssignableFrom(paramType2)) {
      return setter2;
      // 相反
    } else if (paramType2.isAssignableFrom(paramType1)) {
      return setter1;
    }
    // 代理  都没有返回的话  走到这就说明是有问题的  直接添加异常映射关系
    MethodInvoker invoker = new AmbiguousMethodInvoker(setter1,
        MessageFormat.format(
            "Ambiguous setters defined for property ''{0}'' in class ''{1}'' with types ''{2}'' and ''{3}''.",
            property, setter2.getDeclaringClass().getName(), paramType1.getName(), paramType2.getName()));
    setMethods.put(property, invoker);
    Type[] paramTypes = TypeParameterResolver.resolveParamTypes(setter1, type);
    // 返回值映射关系
    setTypes.put(property, typeToClass(paramTypes[0]));
    // 返回null
    return null;
  }

  /**
   * 添加setter映射关系
   */
  private void addSetMethod(String name, Method method) {
    MethodInvoker invoker = new MethodInvoker(method);
    setMethods.put(name, invoker);
    Type[] paramTypes = TypeParameterResolver.resolveParamTypes(method, type);
    setTypes.put(name, typeToClass(paramTypes[0]));
  }

  private Class<?> typeToClass(Type src) {
    Class<?> result = null;
    if (src instanceof Class) {
      result = (Class<?>) src;
    } else if (src instanceof ParameterizedType) {
      result = (Class<?>) ((ParameterizedType) src).getRawType();
    } else if (src instanceof GenericArrayType) {
      Type componentType = ((GenericArrayType) src).getGenericComponentType();
      if (componentType instanceof Class) {
        result = Array.newInstance((Class<?>) componentType, 0).getClass();
      } else {
        Class<?> componentClass = typeToClass(componentType);
        result = Array.newInstance(componentClass, 0).getClass();
      }
    }
    if (result == null) {
      result = Object.class;
    }
    return result;
  }

  /**
   * 字段
   */
  private void addFields(Class<?> clazz) {
    // 获取全部字段
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      // setter不包含这个应该映射
      if (!setMethods.containsKey(field.getName())) {
        // issue #379 - removed the check for final because JDK 1.5 allows
        // modification of final fields through reflection (JSR-133). (JGB)
        // pr #16 - final static can only be set by the classloader
        // 获取类型修饰符  static  final...
        int modifiers = field.getModifiers();
        // 不是static不是final才能继续
        if (!(Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers))) {
          // 添加setter
          addSetField(field);
        }
      }
      // getter不包含这个映射
      if (!getMethods.containsKey(field.getName())) {
        // 添加getter
        addGetField(field);
      }
    }
    // 添加父类的属性
    if (clazz.getSuperclass() != null) {
      addFields(clazz.getSuperclass());
    }
  }

  private void addSetField(Field field) {
    if (isValidPropertyName(field.getName())) {
      setMethods.put(field.getName(), new SetFieldInvoker(field));
      Type fieldType = TypeParameterResolver.resolveFieldType(field, type);
      setTypes.put(field.getName(), typeToClass(fieldType));
    }
  }

  private void addGetField(Field field) {
    if (isValidPropertyName(field.getName())) {
      getMethods.put(field.getName(), new GetFieldInvoker(field));
      Type fieldType = TypeParameterResolver.resolveFieldType(field, type);
      getTypes.put(field.getName(), typeToClass(fieldType));
    }
  }

  private boolean isValidPropertyName(String name) {
    return !(name.startsWith("$") || "serialVersionUID".equals(name) || "class".equals(name));
  }

  /**
   * 这个方法返回一个数组，其中包含在这个类和任何超类中声明的所有方法。我们使用这个方法，而不是更简单的类. getmethods()，因为我们也想寻找私有方法。
   *
   * @param clazz The class
   * @return 包含该类中所有方法的数组
   */
  private Method[] getClassMethods(Class<?> clazz) {
    Map<String, Method> uniqueMethods = new HashMap<>();
    Class<?> currentClass = clazz;
    while (currentClass != null && currentClass != Object.class) {
      // 添加
      addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());

      // 我们还需要寻找接口方法   因为类可能是抽象的
      Class<?>[] interfaces = currentClass.getInterfaces();
      // 添加所有实现的接口的方法
      for (Class<?> anInterface : interfaces) {
        // 添加
        addUniqueMethods(uniqueMethods, anInterface.getMethods());
      }

      // 获取父类的class对象
      currentClass = currentClass.getSuperclass();
    }

    // 获取
    Collection<Method> methods = uniqueMethods.values();
    return methods.toArray(new Method[0]);
  }

  /**
   * 获取唯一的方法
   */
  private void addUniqueMethods(Map<String, Method> uniqueMethods, Method[] methods) {
    // 遍历这个methods获取到所有具体的方法
    for (Method currentMethod : methods) {
      // 桥接方法是 JDK 1.5 引入泛型后，为了使Java的泛型方法生成的字节码和 1.5 版本前的字节码相兼容，由编译器自动生成的方法 isBridge()判断是否是　 FIXME 桥接方法
      if (!currentMethod.isBridge()) {
        // 获取方法的签名
        String signature = getSignature(currentMethod);
        // 检查该方法是否已知
        if (!uniqueMethods.containsKey(signature)) {
          // put
          uniqueMethods.put(signature, currentMethod);
        }
      }
    }
  }

  /**
   * @return 方法签名
   */
  private String getSignature(Method method) {
    StringBuilder sb = new StringBuilder();
    // 返回值class对象
    Class<?> returnType = method.getReturnType();
    // 拼接 FIXME 外部注释为 no-null
    if (returnType != null) {
      sb.append(returnType.getName()).append('#');
    }
    // 方法名
    sb.append(method.getName());
    // 参数class对象数组
    Class<?>[] parameters = method.getParameterTypes();
    // 全拼接
    for (int i = 0; i < parameters.length; i++) {
      sb.append(i == 0 ? ':' : ',').append(parameters[i].getName());
    }
    // 返回
    return sb.toString();
  }

  /**
   * Checks whether can control member accessible.
   *
   * @return If can control member accessible, it return {@literal true}
   * @since 3.5.0
   */
  public static boolean canControlMemberAccessible() {
    try {
      SecurityManager securityManager = System.getSecurityManager();
      if (null != securityManager) {
        securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
      }
    } catch (SecurityException e) {
      return false;
    }
    return true;
  }

  /**
   * Gets the name of the class the instance provides information for.
   *
   * @return The class name
   */
  public Class<?> getType() {
    return type;
  }

  public Constructor<?> getDefaultConstructor() {
    if (defaultConstructor != null) {
      return defaultConstructor;
    } else {
      throw new ReflectionException("There is no default constructor for " + type);
    }
  }

  public boolean hasDefaultConstructor() {
    return defaultConstructor != null;
  }

  public Invoker getSetInvoker(String propertyName) {
    Invoker method = setMethods.get(propertyName);
    if (method == null) {
      throw new ReflectionException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
    }
    return method;
  }

  public Invoker getGetInvoker(String propertyName) {
    Invoker method = getMethods.get(propertyName);
    if (method == null) {
      throw new ReflectionException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
    }
    return method;
  }

  /**
   * Gets the type for a property setter.
   *
   * @param propertyName - the name of the property
   * @return The Class of the property setter
   */
  public Class<?> getSetterType(String propertyName) {
    Class<?> clazz = setTypes.get(propertyName);
    if (clazz == null) {
      throw new ReflectionException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
    }
    return clazz;
  }

  /**
   * Gets the type for a property getter.
   *
   * @param propertyName - the name of the property
   * @return The Class of the property getter
   */
  public Class<?> getGetterType(String propertyName) {
    Class<?> clazz = getTypes.get(propertyName);
    if (clazz == null) {
      throw new ReflectionException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
    }
    return clazz;
  }

  /**
   * Gets an array of the readable properties for an object.
   *
   * @return The array
   */
  public String[] getGetablePropertyNames() {
    return readablePropertyNames;
  }

  /**
   * Gets an array of the writable properties for an object.
   *
   * @return The array
   */
  public String[] getSetablePropertyNames() {
    return writablePropertyNames;
  }

  /**
   * Check to see if a class has a writable property by name.
   *
   * @param propertyName - the name of the property to check
   * @return True if the object has a writable property by the name
   */
  public boolean hasSetter(String propertyName) {
    return setMethods.containsKey(propertyName);
  }

  /**
   * Check to see if a class has a readable property by name.
   *
   * @param propertyName - the name of the property to check
   * @return True if the object has a readable property by the name
   */
  public boolean hasGetter(String propertyName) {
    return getMethods.containsKey(propertyName);
  }

  public String findPropertyName(String name) {
    return caseInsensitivePropertyMap.get(name.toUpperCase(Locale.ENGLISH));
  }
}
