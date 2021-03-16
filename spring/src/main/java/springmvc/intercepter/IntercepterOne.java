package springmvc.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器 by ai q 2021/3/11 11:01  FIXME warning
 */
@Component
public class IntercepterOne implements WebMvcConfigurer, HandlerInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(IntercepterOne.class);

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    logger.info("emmmm.... registry");
    System.out.println("registry");
    registry.addInterceptor(new IntercepterOne());
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.info("come in");
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info("out");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
