package qqai.suanfa.jinjie.dp;

/**
 * 八皇后问题
 *
 * @author qqai
 * @createTime 2020/12/20 17:13
 */
public class EightQueue {

    public static void main(String[] args) {
//        new EightQueue().placeQueens2(8);
        System.out.println(new EightQueue().placeQueens3(8));
    }

    // 数组索引是行号 值是列号
    private int[] cols;

    // 一共的摆法
    private int ways;

    // 从左上角到右下角是否存在皇后
    private boolean leftTop[];

    /// 从右上角到左下角是否有皇后
    private boolean rightTop[];

    // 某一列上是否有皇后
    private boolean colEnd[];

    private int placeQueens3(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 几皇后问题最右边就有几个1 其余全是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0, 0);
    }

    /**
     * 位运算
     *
     * @param limit       固定变量  问题的规模
     * @param colLim      列限制
     * @param leftDiaLim  左斜线的限制
     * @param rightDiaLim 右斜线的限制
     * @return
     */
    private int process(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // 所有列都摆上皇后了 就是齐活
        if (colLim == limit) {
            return 1;
        }
        // colLim | leftDiaLim | rightDiaLim 或出来是总的限制条件，取反之后右边部分为1的是可以尝试的位置
        // 但是取反之后前面会多一串1  然后就与上limit就能得到正确的限制条件 然后所有可尝试的位置都在pos上了
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        int mostRightOne = 0;
        while (pos != 0) {
            // 提取出最右侧的1 = 本身 &（本身取反 + 1）
            mostRightOne = pos & (~pos + 1);
            // 把最右侧的1去掉 然后重回循环就能取到最右边的次一次1
            pos = pos - mostRightOne;
            res += process(
                    // 不变
                    limit,
                    // 取到这个位置的信息之后按位或原来的列信息就能得到下一次递归需要的列信息 把当前列也置为1
                    colLim | mostRightOne,
                    // 左斜线的限制
                    (leftDiaLim | mostRightOne) << 1,
                    // 右斜线的限制
                    (rightDiaLim | mostRightOne) >> 1);
        }
        return res;
    }

    private void placeQueens2(int n) {
        // 0 没有结果
        if (n < 1) throw new RuntimeException("is not allowed zero");
        // 初始化bool数组
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        colEnd = new boolean[n];
        // 递归入口
        place2(0);
        // output
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    private void place2(int row) {
        // 能到最后一行说明八个皇后都摆好了
        if (row == colEnd.length) {
            // 摆法+1
            ways++;
            // 返回
            return;
        }
        //  在当前行的每一列都判断可不可以摆放皇后
        for (int col = 0; col < colEnd.length; col++) {
            // 判断当前行是否有皇后
            if (colEnd[col]) continue;
            // 判断当前↘对角线是否有皇后
            int lIndex = row - col + colEnd.length - 1;
            if (leftTop[lIndex]) continue;
            // 判断↙对角线是否有皇后
            int rIndex = row + col;
            if (rightTop[rIndex]) continue;
            // 都没有就把这一行以及对角线全部变为true
            colEnd[col] = true;
            leftTop[lIndex] = true;
            rightTop[rIndex] = true;
            // 递归 判断下一行
            place2(row + 1);
            //笔记 恢复现场 递归走完之后 如果不把状态修改回来 那么就会拿着true去回溯到上一次的递归调用 就会出错
            colEnd[col] = false;
            leftTop[lIndex] = false;
            rightTop[rIndex] = false;
        }
    }


    /**
     * 开始摆皇后
     *
     * @param n 几个皇后
     */
    public void placeQueens(int n) {
        // 0 没有结果
        if (n < 1) throw new RuntimeException("is not allowed zero");
        // cols初始化
        cols = new int[n];
        // 递归入口
        place(0);
        // output
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 递归方法 从第row行开始摆放皇后
     *
     * @param row 当前行
     */
    private void place(int row) {
        // 能到最后一行说明八个皇后都摆好了
        if (row == cols.length) {
            // 摆法+1
            ways++;
            // 返回
            return;
        }
        //  在当前行的每一列都判断可不可以摆放皇后
        for (int col = 0; col < cols.length; col++) {
            // 判断
            if (isValid(row, col)) {
                // 可以就把这一行的值修改成这一列的下标
                cols[row] = col;
                // 递归 row行已经放了一个了 所以直接从下一行开始摆
                place(row + 1);
            }
        }
    }

    /**
     * 判断row行col列是否可以摆放皇后
     *
     * @param row 行
     * @param col 列
     * @return 可以 true
     */
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col列已经有皇后了
            if (cols[i] == col) return false;
            // 行号相减 等于 列号相减 相等就证明在同一列上 (row - i) / (cols[i] - col) == 1 or -1
            if (row - i == Math.abs(cols[i] - col)) return false;

        }
        return true;
    }
}