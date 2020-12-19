package qqai.bishi.anshuoyun;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 约瑟夫环
 *
 * @author qqai
 * @createTime 2020/12/17 19:31
 */
public class Two {
    public static void main(String[] args) {
        int m = 7;
        int n = 3;
        Queue<Integer> persons = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            persons.add(i + 1);
        }
        int counts = 0;
        while (!persons.isEmpty()) {
            Integer person = persons.poll();
            if (++counts % n == 0) {
                System.out.println(person);
            } else {
                persons.add(person);
            }
        }
    }
}
