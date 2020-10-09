package mybatis.mapper;

import mybatis.entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> queryAll();

    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}