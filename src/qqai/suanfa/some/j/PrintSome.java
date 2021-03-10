package qqai.suanfa.some.j;

/**
 * 输出符号 by ai q 2021/3/8 20:42
 */
public class PrintSome {
  public static void main(String[] args) {
    int n = 34;
    printSome(n);
  }

  private static void printSome(int n) {
    if (n <= 0) {
      return;
    }
    int deep = caclDeep(n);
    System.out.println(deep);
    print(deep);
  }

  private static void print(int n) {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n - i; j++) {
        System.out.print(" ");
      }

      for (int k = 1; k <= 2 * i - 1; k++) {
        System.out.print('*');
      }
      System.out.println();
    }

    for (int i = 1; i <= n - 1; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(" ");
      }

      for (int k = 2 * n - 3; k >= 2 * i - 1; k--) {
        System.out.print('*');
      }
      System.out.println();
    }
  }

  private static int caclDeep(int n) {
    int count = 1;
    int pre = 1;
    int val = 0;
    int deep = 1;

    for (int i = 1; i < n / 2; i++) {
      val = pre + 2;
      count += pre + val;
      if (count > n) {
        return deep;
      }
      pre = val;
      deep += 1;
    }
    return deep;
  }
}
