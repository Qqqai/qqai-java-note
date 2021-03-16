package study.test;

import java.io.IOException;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.dao.UserDao;
import study.entity.User;

/**
 * 测试主程序 by chenaiquan 2021/03/2021/3/12 23.00
 */
public class Main {
  public static final Logger logger = LoggerFactory.getLogger(Main.class);
  private static SqlSession session;

  public static void main(String[] args) throws IOException {
    init();
    UserDao userDao = session.getMapper(UserDao.class);
    List<User> all = userDao.queryAll(null);
    all.forEach(i -> {
      logger.info("user info --> {}", i);
    });
    end();
  }

  public static void init() throws IOException {
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("mybatis.xml"));
    session = factory.openSession();
  }

  private static void end() {
    session.close();
  }
}
