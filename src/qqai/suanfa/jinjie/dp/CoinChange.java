package qqai.suanfa.jinjie.dp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 最佳的找钱的方法
 *
 * @author qqai
 * @createTime 2020/12/21 20:35
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] faces = {100, 50, 20, 10, 5, 1};
        int money = 42;
        List<Integer> list = Arrays.stream(faces).boxed().collect(Collectors.toList());
        //System.out.println(coins2(money, list));
        //System.out.println(Arrays.toString(coinChange(faces, money)));
//        Objects.requireNonNull(coins3(money, list)).forEach(item -> {
//            if (item.length == 1) {
//                System.out.println("num : " + item[0]);
//            } else {
//                System.out.println("info : ");
//                int n = money;
//                while (n > 0) {
//                    System.out.print(item[n] + "\t");
//                    n = n - item[n];
//                }
//            }
//        });
        System.out.println(dp(faces, money));
        System.out.println(process(faces, money, 0));
    }

    /**
     * 动态规划
     *
     * @param faces
     * @param money
     * @return
     */
    public static int dp(int[] faces, int money) {
        int N = faces.length;
        int[][] dp = new int[N + 1][money + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= money; rest++) {

                // 当前位置的面值乘以i张小于rest就进入循环 标记 循环是个枚举得过程
//                for (int i = 0; i * faces[index] <= rest; i++) {
//                    // 递归过程
//                    dp[index][rest] += dp[index + 1][rest - (i * faces[index])];
//                }

                //笔记 改写for循环得枚举行为 dp[index][rest] = dp[index][rest - faces[index]] + dp[index + 1][rest];
                dp[index][rest] = dp[index + 1][rest];
                if (rest - faces[index] >= 0) {
                    dp[index][rest] += dp[index][rest - faces[index]];
                }

            }
        }
        return dp[0][money];
    }

    /**
     * 暴力递归 标记 这个方法求得是一共有多少种解法
     *
     * @param faces 可使用的面值
     * @param rest  还需要多少钱要找
     * @param index faces对应的下标索引
     * @return 方法总数
     */
    public static int process(int[] faces, int rest, int index) {
        // rest不能小于0 小于0就是代表亏了！ 但是因为递归的条件是i * faces[index] <= rest; 所以这里必不小于0
        //        if (rest < 0) {
        //            return 0;
        //        }
        // index == faces.length 就说明index到了faces最后一个元素的后一个位置 如果测试faces不等于0就说明方案失败
        if (index == faces.length) {
            return rest == 0 ? 1 : 0;
        }
        // 总路径
        int ways = 0;
        // 当前位置的面值乘以i张小于rest就进入循环
        for (int i = 0; i * faces[index] <= rest; i++) {
            // 递归过程
            ways += process(faces, rest - (i * faces[index]), index + 1);
        }
        return ways;
    }

    /**
     * dp 暴力递归出所有情况 然后取到最小的
     *
     * @param n    当前需要找的钱
     * @param list 可以找的钱
     * @return 最大值
     */
    public static int coins(int n, List<Integer> list) {
        // 递归基  此处返回一个最大的值
        if (n < 1) return Integer.MAX_VALUE;
        if (list.contains(n)) return 1;
        int min = Integer.MAX_VALUE;
        for (Integer i : list) {
            min = Integer.min(min, coins(n - i, list));
        }
        return min + 1;
    }

    /**
     * 递推 dp  从小到大
     *
     * @param n    当前需要找的钱
     * @param list 可以找的钱
     * @return 最大值
     */
    public static int coins3(int n, List<Integer> list) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        int[] faces = new int[dp.length];
        // 递推过程  标记 因为要求出n位置的值所以 i <= n !!!
        for (int i = 1; i <= n; i++) {
            // 先求dp[1], dp[2], dp[3].... 一直到 dp[n]
            int min = Integer.MAX_VALUE;
            for (Integer integer : list) {
                // 保证min是list中所有情况的最小值 dp[i - integer] < 0 表示这个也没法凑齐所以就不用计算
                if (i >= integer && dp[i - integer] >= 0 && min > dp[i - integer]) {
                    min = dp[i - integer];
                    faces[i] = integer;
                }
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                // 获取dp[i]
                dp[i] = min + 1;
            }
        }
//        // 返回集合
//        List<int[]> result = new ArrayList<>();
//        // 返回dp[n] 就是dp[n]需要的数目
//        result.add(new int[]{dp[n]});
//        // 各个位置需要的钱的列表
//        result.add(faces);
//        // 返回
//        return result;
        return dp[n];
    }

    public static void print(int n, int[] faces) {
        System.out.print("[" + n + "] : ");
        while (n > 0) {
            System.out.print(faces[n] + "\t");
            n = n - faces[n];
        }
        System.out.println();
    }

    /**
     * 记忆化搜索  当前值被求过了就不会再往下计算 dp
     *
     * @param n    当前需要找的钱
     * @param list 可以找的钱
     * @return 最大值
     */
    public static int coins2(int n, List<Integer> list) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        // dp[i] = 1  类似于初始化  把已经确定的比如说i == 1 初始化成1 表示n == 1时就能直接返回1
        for (Integer i : list) {
            if (n < i) continue;
            // 小于n的全部初始化一边
            dp[i] = 1;
        }
        // 递归开始
        return coins2(n, dp, list);
    }

    /**
     * 记忆化搜索 dp  把求过的值保存 再次遇到直接取出来 不进行求解
     *
     * @param n    当前需要找的钱
     * @param list 可以找的钱
     * @return 最大值
     */
    public static int coins2(int n, int[] dp, List<Integer> list) {
        // n < 1 n的格式就有问题返回integer的最大值是为了递归比较的时候可以取到正确的min值
        if (n < 1) return Integer.MAX_VALUE;
        // 如果这个dp[n] == 0 那么这个位置就没有求过
        if (dp[n] == 0) {
            // 进入if开始求解
            int min = Integer.MAX_VALUE;
            // 把每个都算一遍
            for (Integer i : list) {
                min = Integer.min(min, coins2(n - i, dp, list));
            }
            // dp[n] 找钱需要最小的数量(纸张数量就是) min + 1
            dp[n] = min + 1;
        }
        return dp[n];
    }

    /**
     * 该怎么找钱才 标记 贪心策略
     *
     * @param faces 可以找的钱
     * @param money 需要找的钱
     * @return 找钱的序列
     */
    private static int[] coinChange(int[] faces, int money) {
        // 创建一个可变数组（因为找的钱的数量是不一定的）
        List<Integer> list = new ArrayList<>();
        // 大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 可以找的数量全部入堆
        for (int i : faces) {
            heap.offer(i);
        }
        // 只有应该找的钱是0 或者实在没法找了 跳出
        while (money != 0 && !heap.isEmpty()) {
            // 查看栈顶是否是大于应该找的钱的数值
            if (heap.peek() > money) {
                // 大于这个钱就没法找了 所以直接出堆
                heap.poll();
            } else {
                // 如果小于那么可变数组加上这个栈顶
                list.add(heap.peek());
                // 应该找的钱的数值要减去这个栈顶  不能出堆 因为下次还可能找这个面值的钱
                money -= heap.peek();
            }
        }
        // 修改成定长int数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        // 返回
        return res;
    }
}
