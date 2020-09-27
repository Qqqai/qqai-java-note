package qqai.design.filter.inter.impl;

import qqai.design.filter.entity.Person;
import qqai.design.filter.inter.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-15 14:32
 */

public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}