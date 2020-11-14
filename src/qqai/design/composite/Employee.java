package qqai.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：实体对象
 *
 * @author qqai
 * @createTime 2020-09-15 15:10
 */

public class Employee {
    private String name;
    private String dept;
    private int salary;
    //笔记 在实体对象中 定义一个列表包含其他对象就是组合模式
    private List<Employee> subordinates;

    public Employee() {
    }

    //构造函数
    public Employee(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return ("Employee :[ Name : " + name
                + ", dept : " + dept + ", salary :"
                + salary + " ]");
    }
}