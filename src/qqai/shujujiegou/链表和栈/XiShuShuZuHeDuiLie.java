package qqai.shujujiegou.链表和栈;

/**
 * 描述：稀疏数组和队列
 *
 * @author qqai
 * @createTime 2020-09-17 18:31
 */

public class XiShuShuZuHeDuiLie {
    public static void main(String[] args) {
        //创建一个11*11的原始数组
        int[][] old = new int[11][11];
        old[1][2] = 1;
        old[2][3] = 2;
        old[4][5] = 2;
        //输出原始数组
        System.out.println("原始数组....");
        for (int[] i : old) {
            for (int data : i) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("old↑.....");


        System.out.println("xishu...");
        int[][] xiShuShuZu = makeXiShuShuZu(old);
        for (int[] i : xiShuShuZu) {
            for (int data : i) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("xishu↑.....");

        System.out.println("获得原数组信息...");
        int[][] newArray = getOldArrayInfo(xiShuShuZu);
        for (int[] i : newArray) {
            for (int data : i) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("恢复完成...");
    }

    private static int[][] getOldArrayInfo(int[][] xiShuShuZu) {
        int[][] old = new int[xiShuShuZu[0][0]][xiShuShuZu[0][1]];
        for (int i = 1; i < xiShuShuZu.length; i++) {
            old[xiShuShuZu[i][0]][xiShuShuZu[i][1]] = xiShuShuZu[i][2];
        }
        return old;
    }

    private static int[][] makeXiShuShuZu(int[][] old) {
        //遍历获得非0元素的个数
        int sum = 0;
        for (int[] i : old) {
            for (int data : i) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组  这个地方的sum要加一是因为第一行还保存了原数组的结构信息
        int[][] result = new int[sum + 1][3];
        //第一行是原数组的结构信息
        result[0][0] = old.length;
        result[0][1] = old[0].length;
        result[0][2] = 2;
        //顶为稀疏数组添加到哪一行的标志位
        int count = 1;
        for (int i = 0; i < old.length; i++) {
            for (int j = 0; j < old[i].length; j++) {
                if (old[i][j] != 0) {
                    result[count][0] = i;
                    result[count][1] = j;
                    result[count][2] = old[i][j];
                    count++;
                }
            }
        }
        return result;
    }
}
