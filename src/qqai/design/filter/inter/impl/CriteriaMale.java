package qqai.design.filter.inter.impl;

import qqai.design.filter.inter.Criteria;
import qqai.design.filter.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-15 14:26
 */

public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
