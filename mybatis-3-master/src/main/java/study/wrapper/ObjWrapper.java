package study.wrapper;

import java.util.List;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

/**
 * 对象装饰器
 *
 * @author by chenaiquan<likeqiaun@163.com> create on 2021/03/2021/3/27 23.11
 */
public class ObjWrapper implements ObjectWrapper {
  @Override
  public Object get(PropertyTokenizer prop) {
    return null;
  }

  @Override
  public void set(PropertyTokenizer prop, Object value) {

  }

  @Override
  public String findProperty(String name, boolean useCamelCaseMapping) {
    return null;
  }

  @Override
  public String[] getGetterNames() {
    return new String[0];
  }

  @Override
  public String[] getSetterNames() {
    return new String[0];
  }

  @Override
  public Class<?> getSetterType(String name) {
    return null;
  }

  @Override
  public Class<?> getGetterType(String name) {
    return null;
  }

  @Override
  public boolean hasSetter(String name) {
    return false;
  }

  @Override
  public boolean hasGetter(String name) {
    return false;
  }

  @Override
  public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
    return null;
  }

  @Override
  public boolean isCollection() {
    return false;
  }

  @Override
  public void add(Object element) {

  }

  @Override
  public <E> void addAll(List<E> element) {

  }
}
