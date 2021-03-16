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
package org.apache.ibatis.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.Resources;

/**
 * @author Clinton Begin
 */
public class TypeAliasRegistry {

  private final Map<String, Class<?>> typeAliases = new HashMap<>();

  public TypeAliasRegistry() {
    registerAlias("string", String.class);

    registerAlias("byte", Byte.class);
    registerAlias("long", Long.class);
    registerAlias("short", Short.class);
    registerAlias("int", Integer.class);
    registerAlias("integer", Integer.class);
    registerAlias("double", Double.class);
    registerAlias("float", Float.class);
    registerAlias("boolean", Boolean.class);

    registerAlias("byte[]", Byte[].class);
    registerAlias("long[]", Long[].class);
    registerAlias("short[]", Short[].class);
    registerAlias("int[]", Integer[].class);
    registerAlias("integer[]", Integer[].class);
    registerAlias("double[]", Double[].class);
    registerAlias("float[]", Float[].class);
    registerAlias("boolean[]", Boolean[].class);

    registerAlias("_byte", byte.class);
    registerAlias("_long", long.class);
    registerAlias("_short", short.class);
    registerAlias("_int", int.class);
    registerAlias("_integer", int.class);
    registerAlias("_double", double.class);
    registerAlias("_float", float.class);
    registerAlias("_boolean", boolean.class);

    registerAlias("_byte[]", byte[].class);
    registerAlias("_long[]", long[].class);
    registerAlias("_short[]", short[].class);
    registerAlias("_int[]", int[].class);
    registerAlias("_integer[]", int[].class);
    registerAlias("_double[]", double[].class);
    registerAlias("_float[]", float[].class);
    registerAlias("_boolean[]", boolean[].class);

    registerAlias("date", Date.class);
    registerAlias("decimal", BigDecimal.class);
    registerAlias("bigdecimal", BigDecimal.class);
    registerAlias("biginteger", BigInteger.class);
    registerAlias("object", Object.class);

    registerAlias("date[]", Date[].class);
    registerAlias("decimal[]", BigDecimal[].class);
    registerAlias("bigdecimal[]", BigDecimal[].class);
    registerAlias("biginteger[]", BigInteger[].class);
    registerAlias("object[]", Object[].class);

    registerAlias("map", Map.class);
    registerAlias("hashmap", HashMap.class);
    registerAlias("list", List.class);
    registerAlias("arraylist", ArrayList.class);
    registerAlias("collection", Collection.class);
    registerAlias("iterator", Iterator.class);

    registerAlias("ResultSet", ResultSet.class);
  }

  @SuppressWarnings("unchecked")
  /**
   * 根据全限定路径解析class 如果不能分配类型，也会抛出类强制转换异常
   */
  public <T> Class<T> resolveAlias(String string) {
    try {
      if (string == null) {
        return null;
      }
      // issue #748
      String key = string.toLowerCase(Locale.ENGLISH);
      Class<T> value;
      if (typeAliases.containsKey(key)) {
        value = (Class<T>) typeAliases.get(key);
      } else {
        // 用类加载器尝试加载这个类
        value = (Class<T>) Resources.classForName(string);
      }
      return value;
    } catch (ClassNotFoundException e) {
      throw new TypeException("Could not resolve type alias '" + string + "'.  Cause: " + e, e);
    }
  }

  /**
   * 根据包名注册所有别名
   */
  public void registerAliases(String packageName) {
    registerAliases(packageName, Object.class);
  }

  /**
   * 根据包名注册所有别名
   */
  public void registerAliases(String packageName, Class<?> superType) {
    // ResolverUtil用于定位在/a类路径中可用并满足任意条件的类。
    ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<>();
    // 定位
    resolverUtil.find(new ResolverUtil.IsA(superType), packageName);
    // 获取所有可配置类的全限定类名加载出来的class对象
    Set<Class<? extends Class<?>>> typeSet = resolverUtil.getClasses();
    for (Class<?> type : typeSet) {
      // Ignore inner classes and interfaces (including package-info.java)
      // Skip also inner classes. See issue #6
      // java.lang.Class.isAnonymousClass() 当且仅当底层类是匿名类，则返回true。
      // java.lang.Class.isMemberClass() 用于检查基础类是否为成员类。
      if (!type.isAnonymousClass() && !type.isInterface() && !type.isMemberClass()) {
        //注册
        registerAlias(type);
      }
    }
  }

  /**
   * 注册
   *
   * @param type class对象
   */
  public void registerAlias(Class<?> type) {
    // 获取别名
    String alias = type.getSimpleName();
    // 判断是否有Alias注解
    Alias aliasAnnotation = type.getAnnotation(Alias.class);
    if (aliasAnnotation != null) {
      // 有的话按照Alias的value配置别名
      alias = aliasAnnotation.value();
    }
    // 注册
    registerAlias(alias, type);
  }

  /**
   * 注册别名
   *
   * @param alias 别名
   * @param value 别名对应的class对象
   */
  public void registerAlias(String alias, Class<?> value) {
    // 别名不能为空
    if (alias == null) {
      throw new TypeException("The parameter alias cannot be null");
    }
    // issue #748  别名全小写
    String key = alias.toLowerCase(Locale.ENGLISH);
    // 判断是否已存在
    if (typeAliases.containsKey(key) && typeAliases.get(key) != null && !typeAliases.get(key).equals(value)) {
      throw new TypeException("The alias '" + alias + "' is already mapped to the value '" + typeAliases.get(key).getName() + "'.");
    }
    // 添加
    typeAliases.put(key, value);
  }

  /**
   * 注册别名
   *
   * @param alias 别名
   * @param value 别名对应的class对象
   */
  public void registerAlias(String alias, String value) {
    try {
      registerAlias(alias, Resources.classForName(value));
    } catch (ClassNotFoundException e) {
      throw new TypeException("Error registering type alias " + alias + " for " + value + ". Cause: " + e, e);
    }
  }

  /**
   * Gets the type aliases.
   *
   * @return the type aliases
   * @since 3.2.2
   */
  public Map<String, Class<?>> getTypeAliases() {
    return Collections.unmodifiableMap(typeAliases);
  }

}
