package utils;

import org.openjdk.jol.info.ClassLayout;

/**
 * by ai q 2021/3/8 09:58
 */
public class JvmUtils {
  public static void main(String[] args) {
    Object o = new Object();
    System.out.println(ClassLayout.parseInstance(o).toPrintable());
  }
}
