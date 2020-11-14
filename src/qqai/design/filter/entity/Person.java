package qqai.design.filter.entity;

/**
 * 描述：实体对象
 *
 * @author qqai
 * @createTime 2020-09-15 14:20
 */

public class Person {

    private String name;
    private String gender;
    private String maritalStatus;

    public Person() {
    }

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
}