package qqai.java.base;

/**
 * @author qqai
 * @createTime 2020/11/1 20:14
 * @description：重载和重写
 */

public class OverloadAndOverride extends A {

    @Override
    public int funA() {
        return super.funA();
    }

    @Override
    public int funB() {
        return super.funB();
    }

    // Overload
    public String funB(String s) {
        return "S" + s;
    }
}

class A {
    public int funA() {
        return 1;
    }

    public int funB() {
        return 2;
    }
}

