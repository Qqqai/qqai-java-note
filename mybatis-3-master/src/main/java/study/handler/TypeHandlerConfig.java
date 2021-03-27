package study.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import study.constant.CableEnum;
import study.constant.UserCategory;

/**
 * 类型转换器 by chenaiquan 2021/03/2021/3/18 17.39
 */
@MappedTypes(UserCategory.class)
public class TypeHandlerConfig<E extends Enum<E> & CableEnum> extends BaseTypeHandler<E> {
  private final Class<E> type;

  public TypeHandlerConfig(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int value = rs.getInt(columnName);
    if (rs.wasNull()) {
      return null;
    }
    return CableEnum.ofCode(type, value);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int value = rs.getInt(columnIndex);
    if (rs.wasNull()) {
      return null;
    }
    return CableEnum.ofCode(type, value);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int value = cs.getInt(columnIndex);
    if (cs.wasNull()) {
      return null;
    }
    return CableEnum.ofCode(type, value);
  }
}
