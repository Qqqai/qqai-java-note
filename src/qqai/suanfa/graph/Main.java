package qqai.suanfa.graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 测试
 *
 * @author qqai
 * @createTime 2020/12/16 14:32
 */
public class Main {
    public static void main(String[] args) {
//        Graph<String, Integer> graph = new ListGraph<>();
//        graph.addEdge("V1", "V0", 9);
//        graph.addEdge("V1", "V2", 3);
//        graph.addEdge("V2", "V0", 2);
//        graph.addEdge("V2", "V3", 5);
//        graph.addEdge("V3", "V4", 1);
//        graph.addEdge("V0", "V4", 6);
//        graph.removeVerText("V0");
//        graph.print();
//        graph.bfs("V1");
//        testBFS();
//        testDFS();
//        testBFS();
//        testTOPO();
//        testMST();
//        testSP();
//        testSP(2);
        testMultiSP();
    }

    // 权值比较器
    static Graph.WeightManager<Double> weightManager;

    static {
        weightManager = new Graph.WeightManager<>() {
            @Override
            public int compare(Double e1, Double e2) {
                return e1.compareTo(e2);
            }

            @Override
            public Double add(Double e1, Double e2) {
                return e1 + e2;
            }

            @Override
            public Double zero() {
                return 0D;
            }
        };
    }

    private static void testMultiSP() {
        Map<Object, Map<Object, Graph.PathInfo<Object, Double>>> map = undirectedGraph(Data.SP).shortPath();
        map.forEach((k, infoMap) -> {
            System.out.println("========================start==========================");
            System.out.println(k + " 为起点:");
            infoMap.forEach((key, info) -> {
                System.out.println("---------------------------------------------------");
                System.out.println(k + " to " + key + " weight = " + info.weight);
                System.out.println("info:");
                info.info.forEach(item -> {
                    System.out.println(" from : " + item.from + " to : " + item.to + " weight : " + item.weight);
                });
            });
            System.out.println("========================end==========================");
        });
    }

    private static void testSP(int i) {
//        Map<Object, Graph.PathInfo<Object, Double>> map = undirectedGraph(Data.SP).shortPath("A");
        Map<Object, Graph.PathInfo<Object, Double>> map = directedGraph(Data.NEGATIVE_WEIGHT2).shortPath(0);
        map.forEach((k, v) -> {
            System.out.println(k + " weight = " + v.weight);
            System.out.println("info: ");
            v.info.forEach(item -> {
                System.out.println(" from : " + item.from + " to : " + item.to + " weight : " + item.weight);
            });
            System.out.println("---------------------");
        });
    }


    private static void testSP() {
        System.out.println(undirectedGraph(Data.SP).shortPath("A"));
    }

    private static void testMST() {
        Set<Graph.EdgeInfo<Object, Double>> mst = undirectedGraph(Data.MST_01).mst();
        System.out.println(mst);
    }

    private static void testTOPO() {
        List<Object> list = directedGraph(Data.TOPO).topologicalSort();
        System.out.println(list);
    }

    private static void testDFS() {
        directedGraph(Data.DFS_02).dfs("c", value -> {
            System.out.println(value);
            return value.equals("b");
        });
    }

    private static void testBFS() {
        directedGraph(Data.BFS_02).bfs(1, value -> {
            System.out.println(value);
            return false;
        });
    }

    /**
     * 有向图
     *
     * @param data
     * @return
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVerText(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     *
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVerText(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
