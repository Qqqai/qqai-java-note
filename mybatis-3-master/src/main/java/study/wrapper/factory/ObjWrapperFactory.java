package study.wrapper.factory;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * 对象装饰器工厂 by chenaiquan 2021/03/2021/3/27 19.08
 */
public class ObjWrapperFactory implements ObjectWrapperFactory {

  @Override
  public boolean hasWrapperFor(Object object) {
    return false;
  }

  @Override
  public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
    return null;
  }
}
