package qqai.stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 描述：流式编程
 *
 * @author qqai
 * @createTime 2020-09-08 21:04
 */

public class StreamTest {

    public static void main(String[] args) {
        User user1 = new User("qqai", 18, 1);
        User user2 = new User("qqai-zz", 19, 2);
        User user3 = new User("qqai-zz", 20, 3);
        User user4 = new User("qqai-sb", 21, 4);
        User user5 = new User("qqai-sb", 22, 5);
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);
        Map<String, Integer> map = list.stream().collect(groupingBy(User::getUsername, summingInt(User::getAge)));
        System.out.println(map);
        // 笔记 Stream<T> filter(Predicate<? super T> predicate);  Predicate这是函数式接口
        // 笔记 <? super T>是用来限定泛型的作用域的，?代表只要继承自这个泛型T的所有类型都可以成为泛型
        // 笔记 <? super T>同样用来限定泛型，表示只要是T的父类型都可以成为泛型
        // Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getUsername));
        //
        // System.out.println(list.stream().filter((user) -> user.getId() % 2 == 0 && user.getAge() > 20).map(user -> {
        //     String username = user.getUsername();
        //     String lowerCase = username.toUpperCase();
        //     String string = new StringBuffer(lowerCase).reverse().toString();
        //     user.setUsername(string);
        //     return user;
        // }).collect(Collectors.toList()));
        //
        // /**
        //  * 笔记 四大函数式接口
        //  */
        // //笔记 泛型前面的是输入，后面的是返回值
        // Function<String, Integer> function = new Function<String, Integer>() {
        //     @Override
        //     public Integer apply(String s) {
        //         return s.length();
        //     }
        // };
        // System.out.println(function.apply("abc"));
        //
        // Function<String, Integer> f = String::length;
        // System.out.println(f.apply("abc"));
        //
        // Predicate<String> predicate = new Predicate<String>() {
        //     @Override
        //     public boolean test(String s) {
        //         return "abc".equals(s);
        //     }
        // };
        // System.out.println(predicate.test("abc"));
        //
        // Predicate<Integer> p = i -> 1 > i;
        // System.out.println(p.test(3));
        //
        // Consumer<String> consumer = new Consumer<String>() {
        //     @Override
        //     public void accept(String s) {
        //         System.out.println(s);
        //     }
        // };
        // consumer.accept("qqai");
        //
        // Consumer<String> s = System.out::println;
        // s.accept("qqai-zz");
        //
        // Supplier<String> supplier = new Supplier<String>() {
        //     @Override
        //     public String get() {
        //         return "123";
        //     }
        // };
        // System.out.println(supplier.get());
        //
        // Supplier<String> su = () -> "plm";
        // System.out.println(su.get());
    }
}

class User {
    private String username;
    private Integer age;
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public User(String username, Integer age, Integer id) {
        this.username = username;
        this.age = age;
        this.id = id;
    }

    public User() {
    }
}