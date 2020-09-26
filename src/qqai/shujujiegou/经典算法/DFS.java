package qqai.shujujiegou.经典算法;

/**
 * 描述：深度优先搜索
 *
 * @author qqai
 * @createTime 2020-09-22 20:02
 */

public class DFS {
    private int vertexSize;
    private int[] vertexs;
    private int[][] matrix;
    private static final int MAX_WEIGHT = 1000;
    private boolean[] isVisited;

    public DFS(int vertexSize) {
        this.vertexSize = vertexSize;
        matrix = new int[vertexSize][vertexSize];
        vertexs = new int[vertexSize];
        for (int i = 0; i < vertexSize; i++) {
            vertexs[i] = i;
        }
        isVisited = new boolean[vertexSize];
    }

    /*
     * 获取出度
     * */
    public int getOutDegree(int index) {
        int degree = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            int weight = matrix[index][j];
            if (weight != 0 && weight < MAX_WEIGHT) {
                degree++;
            }
        }
        return degree;
    }

    /*
     * 获取权值
     * */
    public int getWeight(int v1, int v2) {
        return matrix[v1][v2] == 0 ? 0 : (matrix[v1][v2] == MAX_WEIGHT ? -1 : matrix[v1][v2]);
    }

    /*
     * 获取第一个邻接点
     * */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexSize; j++) {
            if (matrix[index][j] > 0 && matrix[index][j] < MAX_WEIGHT) {
                return j;
            }
        }
        return -1;
    }

    /*
     * 获取下一个邻接点
     *
     * */
    public int getNextNeighbor(int v, int index) {
        for (int j = index + 1; j < vertexSize; j++) {
            if (matrix[v][j] > 0 && matrix[v][j] < MAX_WEIGHT) {
                return j;
            }
        }
        return -1;
    }


    /*
     * 图的深度优先遍历算法
     *
     * */
    private void depthFirstSearch(int i) {
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                System.out.println("访问到了" + w + "顶点");
                depthFirstSearch(w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 功能描述
     *
     * @param
     * @return
     * @author SGJ
     */
    public void depthFirstSearch() {
        isVisited = new boolean[vertexSize];//所有点重置到没有访问的状态
        for (int i = 0; i < vertexSize; i++) {
            if (!isVisited[i]) {
                System.out.println("访问到了" + i + "顶点");
                depthFirstSearch(i);
            }
        }
        isVisited = new boolean[vertexSize];
    }

    public int[] getVertexs() {
        return vertexs;
    }

    public void setVertexs(int[] vertexs) {
        this.vertexs = vertexs;
    }

    public static void main(String[] args) {
        DFS dfs = new DFS(9);
        int[] a1 = new int[]{0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        int[] a2 = new int[]{10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12};
        int[] a3 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8};
        int[] a4 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21};
        int[] a5 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT};
        int[] a6 = new int[]{11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT};
        int[] a7 = new int[]{MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT};
        int[] a8 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT};
        int[] a9 = new int[]{MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0};
        dfs.matrix[0] = a1;
        dfs.matrix[1] = a2;
        dfs.matrix[2] = a3;
        dfs.matrix[3] = a4;
        dfs.matrix[4] = a5;
        dfs.matrix[5] = a6;
        dfs.matrix[6] = a7;
        dfs.matrix[7] = a8;
        dfs.matrix[8] = a9;

        System.out.println(dfs.getOutDegree(1));
        System.out.println(dfs.getWeight(0, 1));
        dfs.depthFirstSearch();
    }


}