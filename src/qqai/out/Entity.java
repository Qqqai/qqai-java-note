package qqai.out;

/**
 * @author qqai
 * @createTime 2020/11/6 15:10
 * @description：实体
 */

public class Entity {

    private String name;

    private int age;

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public Entity setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Entity setAge(int age) {
        this.age = age;
        return this;
    }
}
