package study.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类 by chenaiquan 2021/03/13 01:21
 */
public class User implements Serializable {
  private static final long serialVersionUID = 665722161038240637L;

  private Integer id;

  private String username;

  private Integer age;

  private String email;

  private String sex;

  private Date birthday;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }


  @Override
  public String toString() {
    return
        "id=" + id +
            ", username='" + username + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", sex='" + sex + '\'' +
            ", birthday=" + birthday;
  }
}
