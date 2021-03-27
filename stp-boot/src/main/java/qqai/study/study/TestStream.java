package qqai.study.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试list集合在为null的情况下 by chenaiquan 2021/03/2021/3/24 22.02
 */
public class TestStream {
  public static void main(String[] args) {
    // List<Integer> list = null;
    // list = list.stream().map(i -> i).collect(Collectors.toList());
    // System.out.println(list);
    // new Test();
    HashMap<String, String> map = new HashMap();
    map.put("a", "a");

    Iterator<String> iterator = map.keySet().iterator();
  }
}

class Test {
  public Test() {
    new Test();
  }
}
