package qqai.test;

import java.util.HashMap;
import java.util.Map;

/**
 * by qqai
 * 2021/1/20 17:30
 */
public class Test10 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        String str = "没人比我更懂java";
        StrObject obj = new StrObject("没人比我更懂java");
        map.put("str", str);
        map.put("obj", obj);

        str = "真的没人比我更懂java";
        System.out.printf(map.get("str").toString() + "; ");

        StrObject new_obj = (StrObject) map.get("obj");
        new_obj.setStr("真的没人比我更懂java");
        System.out.printf(map.get("obj").toString() + "; ");
    }

    static class StrObject {
        String str;

        public StrObject(String str) {
            this.str = str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    static class A {

        class Inner {
            public String v1 = "Fake News";
            public String v2 = "Go ahead";
        }

        private static String GetVal() {
            try {
                return Inner.class.newInstance().v1;
            } catch (Exception e) {
                try {
                    return Inner.class.getDeclaredConstructor(A.class).newInstance((A) null).v2;
                } catch (Exception ee) {
                    ee.printStackTrace();
                    return "Fake News, Go ahead";
                }
            }
        }

        public static void main(String[] args) {

            System.out.println(GetVal());
        }
    }
}
