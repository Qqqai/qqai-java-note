package qqai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import qqai.test.A;
import qqai.test.SpringConfiguration;

/**
 * 描述：
 *
 * @author qqai
 * @createTime 2020-09-26 19:25
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestIoc {

    @Autowired
    A a;

    @Test
    public void t1() {
        a.show();
        a.b.show();
    }
}
