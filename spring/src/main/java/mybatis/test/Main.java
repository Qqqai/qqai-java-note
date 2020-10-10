package mybatis.test;

import mybatis.entity.Student;
import mybatis.mapper.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author qqai
 * @createTime 2020/10/10 09:42
 * @description：获取文件得方式得到sqlsession
 */

public class Main {
    private SqlSession session = null;

    /*笔记 直接获取到项目路径下的SqlMapConfig.xml配置文件 然后开启sqlsession*/
    public StudentDao open(Class<StudentDao> t) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        session = factory.openSession();
        return session.getMapper(t);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        StudentDao studentDao = main.open(StudentDao.class);
        main.commit();
        List<Student> all = studentDao.queryAll();
        for (Student student : all) {
            System.out.println(student);
        }
    }

    public void commit() {
        session.commit();
        session.close();
    }
}
