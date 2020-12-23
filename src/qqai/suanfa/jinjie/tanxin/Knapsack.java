package qqai.suanfa.jinjie.tanxin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 0-1背包问题
 *
 * @author qqai
 * @createTime 2020/12/21 21:07
 */
public class Knapsack {
    public static void main(String[] args) {
        int bag = 150;
        Article[] articles = new Article[]{new Article(35, 10), new Article(30, 40),
                new Article(60, 30), new Article(50, 50),
                new Article(40, 35), new Article(10, 40),
                new Article(25, 30)};

        get(articles, bag);
    }

    /**
     * TODO
     *
     * @param arr
     * @param bag
     */
    private static void get(Article[] arr, int bag) {
        PriorityQueue<Article> valueHeap = new PriorityQueue<>(((o1, o2) -> o2.value - o1.value));
        PriorityQueue<Article> weightHeap = new PriorityQueue<>((Comparator.comparingInt(o -> o.weight)));
        weightHeap.addAll(Arrays.asList(arr));
        while (!weightHeap.isEmpty()) {
            while (!weightHeap.isEmpty() && weightHeap.peek().weight < bag) {
                valueHeap.offer(weightHeap.poll());
            }
            if (valueHeap.isEmpty()) return;
            bag = bag - valueHeap.peek().value;
            System.out.println("背包剩余容量bag = " + bag + " 当前为weight = " + valueHeap.peek().weight + " 价值 value = " + valueHeap.poll().value);
        }
    }

    private static class Article {
        public int weight; // 重量
        public int value;  // 价值

        public Article(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
