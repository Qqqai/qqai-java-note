package study.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import study.constant.ArticleTypeEnum;

/**
 * article枚举处理器
 *
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/4/21 12.35
 */
public class ArticleTypeHandler extends BaseTypeHandler<ArticleTypeEnum> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ArticleTypeEnum parameter, JdbcType jdbcType) throws SQLException {
// 获取枚举的code 值，并设置到 PreparedStatement 中
    ps.setInt(i, parameter.code());
  }

  @Override
  public ArticleTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
    // 从 ResultSet 中获取 code
    int code = rs.getInt(columnName);
// 解析 code 对应的枚举，并返回
    return ArticleTypeEnum.find(code);
  }

  @Override
  public ArticleTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int code = rs.getInt(columnIndex);
    return ArticleTypeEnum.find(code);
  }

  @Override
  public ArticleTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int code = cs.getInt(columnIndex);
    return ArticleTypeEnum.find(code);
  }
}