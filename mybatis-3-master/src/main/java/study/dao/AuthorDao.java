package study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.entity.Author;

/**
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/4/21 12.12
 */
@Mapper
public interface AuthorDao {

  Author findOne(@Param("id") int id);
}
