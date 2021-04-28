package study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import study.entity.Article;

/**
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/4/21 12.32
 */
@Mapper
public interface ArticleDao {
  void select(@Param("id") Integer id, String author, RowBounds rb, Article article);
}
