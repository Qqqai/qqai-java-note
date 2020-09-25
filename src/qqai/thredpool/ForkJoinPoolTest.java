package qqai.thredpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 描述：
 *
 * @author qqai
 * @createTime 2020-09-08 22:26
 */

public class ForkJoinPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //任务线程池
        ForkJoinPool pool = new ForkJoinPool();
        //任务
        MyTask task = new MyTask(0, 100);
        //想线程池提交任务
        ForkJoinTask<Integer> submit = pool.submit(task);
        //获取结果
        System.out.println(submit.get());
        //关闭线程池
        pool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {

    private static final int ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    /**
     * 笔记 二分法
     *
     * @return
     */
    @Override
    protected Integer compute() {
        //判断是否相差大于10
        if ((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            //二分
            int middle = (end + begin) / 2;
            //递归调用 二分前面的部分
            MyTask myTask01 = new MyTask(begin, middle);
            //递归调用 二分后面的部分
            MyTask myTask02 = new MyTask(middle + 1, end);
            myTask01.fork();
            myTask02.fork();
            result = myTask01.join() + myTask02.join();
        }
        return result;
    }

    public MyTask() {
    }

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
