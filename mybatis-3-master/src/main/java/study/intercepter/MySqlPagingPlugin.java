package study.intercepter;

import java.lang.reflect.Field;
import java.util.Properties;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 分页插件
 *
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/4/28 19.56
 */
@Intercepts({
    @Signature(
        type = Executor.class,
        // 目标类
        method = "query",
        // 目标方法
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
    )
})
public class MySqlPagingPlugin implements Interceptor {

  private static final Integer MAPPED_STATEMENT_INDEX = 0;
  private static final Integer PARAMETER_INDEX = 1;
  private static final Integer ROW_BOUNDS_INDEX = 2;


  /**
   * Executor --> <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;
   */
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object[] args = invocation.getArgs();
    // 第三个参数是 rowBound 信息
    RowBounds rowBounds = (RowBounds) args[ROW_BOUNDS_INDEX];
    // 默认
    if (rowBounds == RowBounds.DEFAULT) {
      // 直接执行 不分页
      invocation.proceed();
    }
    // 关闭 mybatis自带的分页
    args[ROW_BOUNDS_INDEX] = RowBounds.DEFAULT;
    //
    MappedStatement ms = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
    BoundSql boundSql = ms.getBoundSql(args[PARAMETER_INDEX]);

    // 拼接sql
    String sql = boundSql.getSql();
    String limit = String.format(
        "LIMIT %d,%d", rowBounds.getOffset(), rowBounds.getLimit());
    sql = sql + " " + limit;

    // 创建一个 StaticSqlSource,并将拼接好的 sql 传入
    SqlSource sqlSource = new StaticSqlSource(
        ms.getConfiguration(), sql, boundSql.getParameterMappings());

    // 反射设置mapperStatement的BoundSql属性
    Field field = MappedStatement.class.getDeclaredField("sqlSource");
    field.setAccessible(true);
    field.set(ms, sqlSource);

    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    return Interceptor.super.plugin(target);
  }

  @Override
  public void setProperties(Properties properties) {
    Interceptor.super.setProperties(properties);
  }
}
