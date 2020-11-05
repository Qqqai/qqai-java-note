package mybatis.test;

import mybatis.entity.Student;
import mybatis.mapper.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/10/10 09:42
 * @description：获取文件得方式得到sqlsession
 */

public class Main {

    /*笔记 直接获取到项目路径下的SqlMapConfig.xml配置文件 然后开启sqlsession*/
    public SqlSession open() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        return factory.openSession();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        SqlSession session = main.open();
        StudentDao studentDao = session.getMapper(StudentDao.class);
//        List<Student> all = studentDao.queryAll();
        studentDao.insert(new Student().setAge(18).setBirthday(new Date()).setName("qqdsad"));
        main.commit(session);
//        for (Student student : all) {
//            System.out.println(student);
//        }
    }

    public void commit(SqlSession session) {
        session.commit();
        // 执行得是JdbcTr..得事物  JdbcTransaction.class
        //   @Override
        //  public void commit() throws SQLException {
        //    if (connection != null && !connection.getAutoCommit()) {
        //      if (log.isDebugEnabled()) {
        //        log.debug("Committing JDBC Connection [" + connection + "]");
        //      }
        //      connection.commit();
        //    }
        //  }
        session.close();
    }
}
