package study.entity;

import java.util.List;
import study.constant.SexEnum;

/**
 * @author by chenaiquan<like.aiquan@qq.com> create on 2021/4/21 12.07
 */
public class Author{
private Integer id;
private String name;
private Integer age;
private SexEnum sex;
private String email;
private List<Article> articles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public SexEnum getSex() {
    return sex;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  @Override
  public String toString() {
    return "Author{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", sex=" + sex +
        ", email='" + email + '\'' +
        ", articles=" + articles +
        '}';
  }
}

