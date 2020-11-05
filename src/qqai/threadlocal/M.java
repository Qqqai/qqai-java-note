package qqai.threadlocal;

/**
 * @author qqai
 * @createTime 2020/11/5 20:17
 * @description：标准类
 */

public class M {
    private String a;

    public M setA(String a) {
        this.a = a;
        return this;
    }

    public String getA() {
        return a;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize......");
    }
}
