package study.test;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.dao.ArticleDao;
import study.dao.UserDao;
import study.entity.Article;
import study.entity.User;

/**
 * 测试主程序 by chenaiquan 2021/03/2021/3/12 23.00
 */
public class Main {
  public static final Logger log = LoggerFactory.getLogger(Main.class);
  private static SqlSession session;

  public static void main(String[] args) throws IOException, NoSuchMethodException {
    // testSelect();
    // testUpdate();
    // testDBPOOD();
    testPlugin();
  }

  public static void testPlugin() throws IOException {
    init();
    UserDao userDao = session.getMapper(UserDao.class);
    List<User> list = userDao.queryAll(null, new RowBounds(2, 2));
    list.forEach(i -> {
      log.info(i.toString());
    });
  }

  public static void testDBPOOD() throws IOException {
    init();
    session.getConnection();
  }

  private static void testUpdate() throws IOException {
    User user = new User();
    user.setId(1);
    user.setUsername("sb");
    init();
    UserDao userDao = session.getMapper(UserDao.class);
    int i = userDao.update(user);
    log.info("update row number : {}", i);
  }


  public static void testWhereSqlNode() {
    String sqlFragment = "AND id = #{id}";
    MixedSqlNode msn = new MixedSqlNode(Collections.singletonList(new StaticTextSqlNode(sqlFragment)));
    WhereSqlNode wsn = new WhereSqlNode(new Configuration(), msn);
    DynamicContext dc = new DynamicContext(new Configuration(), new ParamMap<>());
    wsn.apply(dc);
    System.out.println("解析前:" + sqlFragment);
    System.out.println("解析后:" + dc.getSql());
  }

  public static void MethodSignatureGetParams() throws NoSuchMethodException {
    Configuration configuration = new Configuration();
    configuration.setUseActualParamName(false);
    Method method = ArticleDao.class.getMethod("select", Integer.class, String.class, RowBounds.class, Article.class);
    ParamNameResolver paramNameResolver = new ParamNameResolver(configuration, method);
    System.out.println(Arrays.toString(paramNameResolver.getNames()));
  }

  public static void testSelect() throws IOException {
    init();
    // 获得userDao的代理对象
    UserDao userDao = session.getMapper(UserDao.class);
    // 执行代理方法
    List<User> all = new ArrayList<>();
    // all = userDao.queryAll(null);
    List<User> user = userDao.queryAll(new User(1), new RowBounds());
    all.add(user.get(0));
    all.forEach(i -> {
      log.info("user info --> {}", i);
    });
    end();
  }

  public static void getConfigResultMap() throws IOException {
    // InputStream inputStream, Configuration configuration, String resource, Map<String, XNode> sqlFragments
    Configuration configuration = new Configuration();
    InputStream inputStream = Resources.getResourceAsStream("mapper/UserDao.xml");
    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, "mapper/UserDao.xml", configuration.getSqlFragments());
    xmlMapperBuilder.parse();
    configuration.getResultMaps().forEach(i -> {
      log.info(i.toString());
    });
  }


  public static void init() throws IOException {
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    // 根据全局配置文件构建mybatis的配置信息
    SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("mybatis.xml"));
    // 打开一个数据库访问session
    session = factory.openSession();
  }

  private static void end() {
    session.close();
  }

  public static void zhehang(String str) {
    str = "//DEF=BLKSIZE=12345,DCB=BLKSIZE=" +
        "26400,SPACE=(TRK,(10,3),RLSE),HE" +
        "AD=(RDB,(2,5),RBQ)";
    int flag = 0;
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ',' && flag == 0) {
        System.out.println(res);
        res = new StringBuilder();
        continue;
      }
      if (str.charAt(i) == '(') {
        flag += 1;
      }
      if (str.charAt(i) == ')') {
        flag -= 1;
      }
      res.append(str.charAt(i));
    }
    System.out.println(res);
  }
}