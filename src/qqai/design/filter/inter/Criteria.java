package qqai.design.filter.inter;

import qqai.design.filter.entity.Person;

import java.util.List;

/**
 * 描述：为过滤标准（Criteria）创建一个接口。
 *
 * @author qqai
 * @createTime 2020-09-15 14:21
 */

public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}