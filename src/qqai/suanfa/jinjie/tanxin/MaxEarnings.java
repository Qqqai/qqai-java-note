package qqai.suanfa.jinjie.tanxin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author qqai
 * @createTime 2020/12/15 00:37
 * @description：最大收益
 */

public class MaxEarnings {

    public static void main(String[] args) {
        System.out.println(findMaximizedCapital(4, 100, new int[]{15, 14, 13, 12}, new int[]{100, 110, 100, 90}));
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // node是项目类  类中p代表收益  c代表花费
        Node[] nodes = new Node[profits.length];
        // 为每个项目新建这么一个类
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(profits[i], capital[i]);
        }
        // 用比较器构造一个大根堆
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        // 用比较器构造一个小根堆
        PriorityQueue<Node> minCostQ = new PriorityQueue<>((Comparator.comparingInt(o -> o.c)));
        // 把node中所有的元素都放进小根堆中  默认是使用节点的c也就是花费来排序
        minCostQ.addAll(Arrays.asList(nodes));
        // 要进行几次项目
        for (int i = 0; i < k; i++) {
            // 把小根堆中所有花费小于w也就是初始资金的项目全部加入大根堆
            while (!minCostQ.isEmpty() && minCostQ.peek().c < w) {
                // 大根堆是按照节点的p也就是收益来排序 也就是最大收益会在堆顶
                maxProfitQ.add(minCostQ.poll());
            }
            // 如果大根堆没有元素 可能是小根堆空了  也可能是剩下的资金不够去买其余的项目
            if (maxProfitQ.isEmpty()) return w;
            // 项目初始资金加上每次获取的收益就是可用资金
            w += maxProfitQ.poll().p;
        }
        return w;
    }

    /*项目*/
    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "p=" + p +
                    ", c=" + c +
                    '}';
        }
    }
}
