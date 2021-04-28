package study.entity;

import java.util.Date;
import study.constant.ArticleTypeEnum;

/**
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/4/21 12.07
 */
public class Article {
  private Integer id;
  private String title;
  private ArticleTypeEnum type;
  private Author author;
  private String content;
  private Date createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArticleTypeEnum getType() {
    return type;
  }

  public void setType(ArticleTypeEnum type) {
    this.type = type;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", type=" + type +
        ", author=" + author +
        ", content='" + content + '\'' +
        ", createTime=" + createTime +
        '}';
  }
}

