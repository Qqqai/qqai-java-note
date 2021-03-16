package study.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.entity.User;

/**
 * (User)表数据库访问层 by chenaiquan 2021/03/12 23:10
 */
@Mapper
public interface UserDao {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  User queryById(@Param("id") Integer id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param user 实例对象
   * @return 对象列表
   */
  List<User> queryAll(User user);

  /**
   * 新增数据
   *
   * @param user 实例对象
   * @return 影响行数
   */
  int insert(User user);

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<User> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<User> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<User> 实例对象列表
   * @return 影响行数
   */
  int insertOrUpdateBatch(@Param("entities") List<User> entities);

  /**
   * 修改数据
   *
   * @param user 实例对象
   * @return 影响行数
   */
  int update(User user);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

}

