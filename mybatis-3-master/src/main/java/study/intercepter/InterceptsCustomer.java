package study.intercepter;

import java.sql.Connection;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义mybatis拦截器
 *
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/3/29 19.26
 */
@Intercepts({
    @Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
    )
})
public class InterceptsCustomer implements Interceptor {
  private final Logger log = LoggerFactory.getLogger(InterceptsCustomer.class);

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    log.info("before interceptor.... obj: {}, method: {}, args:{}", invocation.getTarget(), invocation.getMethod(), invocation.getArgs());
    return invocation.proceed();
  }
}
