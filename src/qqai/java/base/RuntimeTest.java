package qqai.java.base;

import java.io.IOException;
import java.util.Scanner;

/**
 * by ai q 2021/3/6 18:59
 */
public class RuntimeTest {
  public static void main(String[] args) throws IOException {
    String s = "windows";
    s = new Scanner(System.in).next();
    if (s.equals("windows")) {
      Runtime.getRuntime().exec("C:\\Windows\\System32/cmd.exe /k start cmd");
    }
  }
}
