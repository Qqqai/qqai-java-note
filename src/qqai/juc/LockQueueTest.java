package qqai.juc;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 描述：阻塞队列测试
 *
 * @author qqai
 * @createTime 2020-09-08 14:47
 */

public class LockQueueTest {
    /*
   笔记 阻塞队列：
        *ArrayBlockingQueue:由数组结构组成的有界阻塞队列。
        *LinkedBlockingQueue:由链表结构组成的有界（但大小默认值为integer.MAX_VALUE)阻塞队列。
        PriorityBlockingQueue:支持优先级排序的无界阻塞队列。
        *SynchronousQueue:不存储元素的阻塞队列，也即单个元素的队列。
        LinkedTransferQueue:由链表组成的无界阻塞队列。
        LinkedBlockingDeque:由链表组成的双向阻塞队列。
        DelayQueue:使用优先级队列实现的延迟无
     */
    public static void main(String[] args) {
//        Collection collection = null;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);//有界
//        blockingQueue.add("aaaa");
//        blockingQueue.add("bbbb");
//        blockingQueue.add("cccc");
        //笔记 第四个添加异常：java.lang.IllegalStateException: Queue full
//        blockingQueue.add("aaaa");
        //笔记 队列先进先出
//        blockingQueue.remove();  //第一个先出
//        System.out.println(blockingQueue);
//        blockingQueue.remove();
//        blockingQueue.remove();
        //笔记 第四次remove异常: java.util.NoSuchElementException
//        blockingQueue.remove();
        //笔记 检查队首元素
//        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        //这里第四次添加出现false
//        System.out.println(blockingQueue.offer("a"));
    }

}

