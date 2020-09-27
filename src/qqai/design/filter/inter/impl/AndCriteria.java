package qqai.design.filter.inter.impl;

import qqai.design.filter.entity.Person;
import qqai.design.filter.inter.Criteria;

import java.util.List;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-15 14:32
 */

public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}