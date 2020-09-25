package qqai.hashequal;

import java.util.Objects;

/**
 * 描述：hashCode和equal测试
 *
 * @author qqai
 * @createTime 2020-09-16 13:57
 */

public class Main {
    private String name;
    private int age;

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
