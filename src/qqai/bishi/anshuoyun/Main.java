package qqai.bishi.anshuoyun;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * 安硕云笔试
 *
 * @author qqai
 * @createTime 2020/12/17 18:46
 */
public class Main {
    public static void main(String[] args) {
        CreditApply zs = new CreditApply("1001", "张三", "抵押", 100000.5);
        CreditApply ls = new CreditApply("1002", " 李四", "质押", 50000);
        CreditApply ww = new CreditApply("1003", "王五", "质押", 30000);
        CreditApply zl = new CreditApply("1004", "赵六", "抵押", 60000.51);
        try {
            FileOutputStream outputStream = new FileOutputStream("d:/credit.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeChars(zs.toString());
            objectOutputStream.writeChars(ls.toString());
            objectOutputStream.writeChars(ww.toString());
            objectOutputStream.writeChars(zl.toString());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class CreditApply {
        private String serialno;
        private String name;
        private String vouchType;
        private double sum;

        public CreditApply(String serialno, String name, String vouchType, double sum) {
            this.serialno = serialno;
            this.name = name;
            this.vouchType = vouchType;
            this.sum = sum;
        }
        public String getSerialno() {
            return serialno;
        }
        public String getName() {
            return name;
        }
        public String getVouchType() {
            return vouchType;
        }
        public double getSum() {
            return sum;
        }
        @Override
        public String toString() {
            return "(" +
                    "serialno='" + serialno + '\'' +
                    ", name='" + name + '\'' +
                    ", vouchType='" + vouchType + '\'' +
                    ", sum=" + sum +
                    ')';
        }
    }
}
