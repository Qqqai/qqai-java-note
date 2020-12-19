package qqai.suanfa.graph;


import java.util.*;

/**
 * 图
 *
 * @author qqai
 * @createTime 2020/12/16 14:32
 */
public interface Graph<V, E> {

    Map<V, E> Dijkstra(V begin, WeightManager<E> weightManager);

    Map<V, PathInfo<V, E>> DijkstraBetter(V begin, WeightManager<E> weightManager);

    List<V> topologicalSort();

    void bfs(V begin, VertexVisitor<V> visitor);

    void dfs(V begin, VertexVisitor<V> visitor);

    int edgesSize();

    void print();

    int vertices();

    void addVerText(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVerText(V v);

    void removeEdge(V from, V to);

    Set<EdgeInfo<V, E>> mst(WeightManager<E> weightManager);

    interface VertexVisitor<V> {
        boolean visit(V v);
    }

    class PathInfo<V, E> {
        E weight;
        List<EdgeInfo<V, E>> info = new LinkedList<>();
    }

    /**
     * 权值管理器
     *
     * @param <E>
     */
    interface WeightManager<E> {

        default int compare(E e1, E e2) {
            return 1;
        }

        default E add(E e1, E e2) {
            return null;
        }
    }

    class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}'
                    + "\n";
        }

        protected EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
