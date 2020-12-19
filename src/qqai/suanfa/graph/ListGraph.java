package qqai.suanfa.graph;

import java.util.*;

/**
 * @author qqai
 * @createTime 2020/12/16 14:37
 */
@SuppressWarnings("unchecked")
public class ListGraph<V, E> implements Graph<V, E> {

    // 全局顶点表
    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();

    // 全局边集
    private final Set<Edge<V, E>> edges = new HashSet<>();

    // 权值管理器
    private WeightManager<E> weightManager;
    // 比较器
    private final Comparator<Edge<V, E>> comparator = (o1, o2) -> weightManager.compare(o1.weight, o2.weight);

    /**
     * 创建图必须给定一个权值解释器
     *
     * @param weightManager 权值解释器
     */
    public ListGraph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    /**
     * 打印全局顶点和边信息
     */
    public void print() {
        System.out.println("顶点------------------------");
        vertices.forEach((v, veVertex) -> {
            System.out.println(v);
            System.out.println("-----out-- ----");
            System.out.println(veVertex.outEdge);
            System.out.println("-----in-------");
            System.out.println(veVertex.inEdge);
        });
        System.out.println("边------------------------");
        edges.forEach(System.out::println);
    }

    /**
     * 多源最短路径 标记 Floyd算法
     *
     * @return 最短路径表
     */
    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortPath() {
        // 初始化路径表
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        // 把所有的边的各类信息放进区
        edges.forEach(edge -> {
            // 从paths中拿到以edge节点出发到它直接链接的所有节点的Map<to,pathInfo>信息 如果没有这个信息就创建创建一个
            Map<V, PathInfo<V, E>> map = paths.computeIfAbsent(edge.from.value, k -> new HashMap<>());
//            Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
//            if (map == null) {
//                map = new HashMap<>();
//                paths.put(edge.from.value, map);
//            }
            // 创建一个PathInfo对象
            PathInfo<V, E> info = new PathInfo<>();
            // info 的权值是当前边的权值
            info.weight = edge.weight;
            // info的info用有序链表保存这个边的EdgeInfo信息
            info.info.add(edge.info());
            // map中保存的是入度顶点和pathInfo信息
            map.put(edge.to.value, info);
        });
        // 遍历全局顶点信息 三次 获取三个节点信息
        vertices.forEach((k2, v2) -> {
            vertices.forEach((k1, v1) -> {
                vertices.forEach((k3, v3) -> {
                    // 如果这三个对象是有两个是同一个对象那就不需要比较
                    if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)) return;
                    // v1 - v2  path
                    PathInfo<V, E> v1tov2 = getPathInfo(k1, k2, paths);
                    if (v1tov2 == null) return;
                    // v2 - v3  path
                    PathInfo<V, E> v2tov3 = getPathInfo(k2, k3, paths);
                    if (v2tov3 == null) return;
                    // v1 - v3  path
                    PathInfo<V, E> v1tov3 = getPathInfo(k1, k3, paths);
                    // compare
                    E newWeight = weightManager.add(v1tov2.weight, v2tov3.weight);
                    // like A-D compare A-D-E  如果权值比较新的权值边比较大那么就不要后续操作
                    if (v1tov3 != null && weightManager.compare(newWeight, v1tov3.weight) >= 0)
                        return;
                    // 如果k1顶点到k3顶点没有信息就说明k1没有到k3的权值最小边还没有被计算过
                    if (v1tov3 == null) {
                        // 新建一个PathInfo表示k1-k3的权值边信息
                        v1tov3 = new PathInfo<>();
                        // get到k1为起点的map然后把k3作为终点的权值边信息放进去
                        paths.get(k1).put(k3, v1tov3);
                    } else {
                        // 如果这个存在那么就需要清空原有的info信息然后设置新的info信息进去
                        v1tov3.info.clear();
                    }
                    // 设置新权值
                    v1tov3.weight = newWeight;
                    // 把k1-k2的权值边信息设置进去
                    v1tov3.info.addAll(v1tov2.info);
                    // 把k2-k3的权值边信息也设置进去
                    v1tov3.info.addAll(v2tov3.info);
                });
            });
        });
        // 返回权值路径表
        return paths;
    }

    /**
     * 获取从某个顶点到另一个顶点的权值边信息
     *
     * @param from  起点
     * @param to    终点
     * @param paths 全职路径表
     * @return 如果存在返回 如果不存在返回null
     */
    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }

    /**
     * 单源最短路径
     *
     * @param begin 源节点
     * @return 最短路径表
     */
    @Override
    public Map<V, PathInfo<V, E>> shortPath(V begin) {
        //  return this.Dijkstra(begin);
        //  return this.DijkstraBetter(begin);
        return this.BellmanFord(begin);
    }

    /**
     * BellmanFord()算法 单源最短路径算法
     *
     * @param begin 起点的值
     * @return 最短路径表
     */
    private Map<V, PathInfo<V, E>> BellmanFord(V begin) {
        // 获取源节点信息
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) throw new RuntimeException("源节点不存在");
        // 返回表
        HashMap<V, PathInfo<V, E>> selected = new HashMap<>();
        // 第一次返回表中没有数据 所以把初始节点的最短路径看成 0 或者 空 放进去
        PathInfo<V, E> info = new PathInfo<>();
        // 从权值管理器获取到初始节点的最短路径 也就是为0是什么情况
        info.weight = weightManager.zero();
        // 选择
        selected.put(begin, info);
        // 笔记 算法思想
        for (int i = 0; i < vertices.size() - 1; i++) {
            // 遍历每条边
            edges.forEach(edge -> {
                // 松弛 要先获取到from点的最短权值路径
                PathInfo<V, E> from = selected.get(edge.from.value);
                // 松弛操作
                relaxForBellmanFord(weightManager, from, edge, selected);
            });
        }
        // 标记 判断是否存在负权环  笔记 上面每个边已经松弛了V-1次了如果次数还能松弛成功那么就存在负权环 所以找不到最短路径
        edges.forEach(edge -> {
            PathInfo<V, E> from = selected.get(edge.from.value);
            if (relaxForBellmanFord(weightManager, from, edge, selected)) throw new RuntimeException("存在负权环，无法找到最短路径");
        });
        // 删除放进区的起点
        selected.remove(begin);
        // 返回
        return selected;
    }

    /**
     * 松弛 服务于 BellmanFord()
     *
     * @param weightManager 权值比较器
     * @param fromPath      当前取得的最小节点的最小权值边
     * @param edge          当前边
     * @param paths         可选边集
     */
    private boolean relaxForBellmanFord(WeightManager<E> weightManager, PathInfo<V, E> fromPath, Edge<V, E> edge, Map<V, PathInfo<V, E>> paths) {
        if (fromPath == null) return false;
        /* 比较 A-E 和 A-D-E的权值哪个小 */
        // 标记 新的可选路径 minEntry的value保存的就是起始节点到minEntry节点权值最小的的路径 加上当前边的权值就是现在的路径权值大小
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        // 此处可能为空 标记 以前的路径 当前边的入度节点以前的路径
        PathInfo<V, E> oldWeight = paths.get(edge.to.value);
        //更新新的可选路径 如果新的路径比以前的路径小的情况下
        if (oldWeight != null && weightManager.compare(newWeight, oldWeight.weight) >= 0) return false;
        // 新建一个返回信息表
        PathInfo<V, E> pathInfo = new PathInfo<>();
        // 权值就是现在最小的权值
        pathInfo.weight = newWeight;
        // info里面保存的就是所有的可选边的信息
        pathInfo.info.addAll(fromPath.info);
        // 再把现在这条边加进去
        pathInfo.info.add(edge.info());
        // 更新这个节点的权值路径添加到可选表中
        paths.put(edge.to.value, pathInfo);
        return true;
    }


    /**
     * 最短路径  标记 不能有负权边！！
     *
     * @param begin 开始节点
     * @return 到各个顶点的距离的返回
     */
    private Map<V, E> Dijkstra(V begin) {
        // 获取初始开始节点的信息
        Vertex<V, E> beginVertex = vertices.get(begin);
        // 如果无此节点信息 返回null
        if (beginVertex == null) throw new RuntimeException("nothing...");
        // 选择过的表
        Map<V, E> selectedPath = new HashMap<>();
        // 可选表
        Map<Vertex<V, E>, E> paths = new HashMap<>();
        // 初始节点的松弛
        for (Edge<V, E> edge : beginVertex.outEdge) {
            // 把初始节点到其他节点的边的权值和入读节点存到表中
            paths.put(edge.to, edge.weight);
        }
        // 只要这个表不是空的
        while (!paths.isEmpty()) {
            // 从表中获取一个权值路径最小的节点
            Map.Entry<Vertex<V, E>, E> minEntry = this.getMinPath(paths);
            // 松弛
            Vertex<V, E> minVertex = minEntry.getKey();
            // 表示这个节点被提起来了
            selectedPath.put(minVertex.value, minEntry.getValue());
            // 从可选节点删除这个节点
            paths.remove(minVertex);
            // 遍历这个最小节点的所有出度边
            for (Edge<V, E> edge : minVertex.outEdge) {
                // 如果当前的入度节点已经离开桌面 == 已经存在于selectedPath中 那么就不需要进行松弛操作
                if (selectedPath.containsKey(edge.to.value)) continue;
                /* 比较 A-E 和 A-D-E的权值哪个小 */
                // 标记 新的可选路径 minEntry的value保存的就是起始节点到minEntry节点权值最小的的路径 加上当前边的权值就是现在的路径权值大小
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                // 此处可能为空 标记 以前的路径 当前边的入度节点以前的路径
                E oldWeight = paths.get(edge.to);
                //更新新的可选路径 如果新的路径比以前的路径小的情况下
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    // 更新这个节点的权值路径添加到可选表中
                    paths.put(edge.to, newWeight);
                }
            }
        }
        // 返回所有被提起来的节点表 无向图的话这个表可能会包含开始节点 所以删除一下
        selectedPath.remove(beginVertex.value);
        return selectedPath;
    }

    /**
     * 从paths中获取路径最短的一个节点 被提起来的节点
     *
     * @param paths 路径表
     * @return 节点
     */
    private Map.Entry<Vertex<V, E>, E> getMinPath(Map<Vertex<V, E>, E> paths) {
        // 迭代器
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        // 首先获取一个节点
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        // 迭代器还能那到内容就遍历
        while (iterator.hasNext()) {
            // 获取
            Map.Entry<Vertex<V, E>, E> next = iterator.next();
            // 用权值比较器比较那个节点的权值路径最小
            if (weightManager.compare(next.getValue(), minEntry.getValue()) < 0) {
                // 更新最小节点
                minEntry = next;
            }
        }
        // 返回最小节点信息
        return minEntry;
    }

    /**
     * 单源最短路径算法 标记 不能有负权边！！
     *
     * @param begin 源节点
     * @return 单源最短路径表
     */
    private Map<V, PathInfo<V, E>> DijkstraBetter(V begin) {
        // 获取初始开始节点的信息
        Vertex<V, E> beginVertex = vertices.get(begin);
        // 如果无此节点信息 返回null
        if (beginVertex == null) throw new RuntimeException("nothing...");
        // 选择过的表
        Map<V, PathInfo<V, E>> selectedPath = new HashMap<>();
        // 可选表
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        // 初始节点的松弛
        for (Edge<V, E> edge : beginVertex.outEdge) {
            PathInfo<V, E> info = new PathInfo<>();
            info.weight = edge.weight;
            info.info.add(edge.info());
            // 把初始节点到其他节点的边的权值和入读节点存到表中
            paths.put(edge.to, info);
        }
        // 只要这个表不是空的
        while (!paths.isEmpty()) {
            // 从表中获取一个权值路径最小的节点
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = this.getMinPathBetter(paths);
            // 松弛
            Vertex<V, E> minVertex = minEntry.getKey();
            // 表示这个节点被提起来了
            PathInfo<V, E> info = new PathInfo<>();
            // 更新边的权值
            info.weight = minEntry.getValue().weight;
            // 添加节点的最小边信息
            info.info.addAll(minEntry.getValue().info);
            // 在选择过的节点添加这个顶点
            selectedPath.put(minVertex.value, info);
            // 从可选节点删除这个节点
            paths.remove(minVertex);
            // 遍历这个最小节点的所有出度边
            for (Edge<V, E> edge : minVertex.outEdge) {
                // 如果当前的入度节点已经离开桌面 == 已经存在于selectedPath中 那么就不需要进行松弛操作
                if (selectedPath.containsKey(edge.to.value)) continue;
                // 获得minEntry的权值最小路径
                PathInfo<V, E> fromPath = minEntry.getValue();
                // 松弛
                relaxForDijkstra(weightManager, fromPath, edge, paths);
            }
        }
        // 返回所有被提起来的节点表 无向图的话这个表可能会包含开始节点 所以删除一下
        selectedPath.remove(beginVertex.value);
        return selectedPath;
    }

    /**
     * 松弛 服务于Dijkstra()
     *
     * @param weightManager 权值比较器
     * @param fromPath      当前取得的最小节点的最小权值边
     * @param edge          当前边
     * @param paths         可选边集
     */
    private void relaxForDijkstra(WeightManager<E> weightManager, PathInfo<V, E> fromPath, Edge<V, E> edge, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        /* 比较 A-E 和 A-D-E的权值哪个小 */
        // 标记 新的可选路径 minEntry的value保存的就是起始节点到minEntry节点权值最小的的路径 加上当前边的权值就是现在的路径权值大小
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        // 此处可能为空 标记 以前的路径 当前边的入度节点以前的路径
        PathInfo<V, E> oldWeight = paths.get(edge.to);
        //更新新的可选路径 如果新的路径比以前的路径小的情况下
        if (oldWeight != null && weightManager.compare(newWeight, oldWeight.weight) >= 0) return;
        // 新建一个返回信息表
        PathInfo<V, E> pathInfo = new PathInfo<>();
        // 权值就是现在最小的权值
        pathInfo.weight = newWeight;
        // info里面保存的就是所有的可选边的信息
        pathInfo.info.addAll(fromPath.info);
        // 再把现在这条边加进去
        pathInfo.info.add(edge.info());
        // 更新这个节点的权值路径添加到可选表中
        paths.put(edge.to, pathInfo);
    }

    /**
     * 从paths中获取路径最短的一个节点 被提起来的节点  服务于 DijkstraBetter()
     *
     * @param paths 路径表
     * @return 节点
     */
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPathBetter(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        // 迭代器
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        // 首先获取一个节点
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        // 迭代器还能那到内容就遍历
        while (iterator.hasNext()) {
            // 获取
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> next = iterator.next();
            // 用权值比较器比较那个节点的权值路径最小
            if (weightManager.compare(next.getValue().weight, minEntry.getValue().weight) < 0) {
                // 更新最小节点
                minEntry = next;
            }
        }
        // 返回最小节点信息
        return minEntry;
    }

    /**
     * 拓扑排序 标记 必须是有向无环图才能进行拓扑排序
     *
     * @return 排序后的节点顺序保存并返回
     */
    @Override
    public List<V> topologicalSort() {
        // 创建保存数组
        List<V> list = new ArrayList<>();
        // 创建队列
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 创建map 相当于每个节点的入度表
        Map<Vertex<V, E>, Integer> map = new HashMap<>();
        // 初始化 入度位0的节点全部放进队列
        vertices.forEach((k, v) -> {
            if (v.inEdge.size() == 0) {
                // 入队
                queue.offer(v);
            } else {
                // 入表
                map.put(v, v.inEdge.size());
            }
        });
        // 如果队列不为空继续遍历 标记 队列中全都是入度为0的节点
        while (!queue.isEmpty()) {
            // 出对元素
            Vertex<V, E> v = queue.poll();
            // 添加
            list.add(v.value);
            // 遍历出度集合
            v.outEdge.forEach(veEdge -> {
                // 获取当前出度节点的入度表
                int i = map.get(veEdge.to) - 1;
                // 入度为0
                if (i == 0) {
                    // 入队
                    queue.offer(veEdge.to);
                } else {
                    // 更新当前节点的入度值
                    map.put(veEdge.to, i);
                }
            });
        }
        return list;
    }

    /**
     * 广度优先搜索
     *
     * @param begin   起点
     * @param visitor 访问控制器
     */
    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        // 起点不能为空
        if (visitor == null) return;
        // 全局获取起点信息
        Vertex<V, E> veVertex = vertices.get(begin);
        // 全局没有保存起点信息  表示 没有这个节点 return
        if (veVertex == null) throw new RuntimeException("nothing...");
        // 创建队列
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 起点入队
        queue.offer(veVertex);
        // 创建set去重
        Set<Vertex<V, E>> set = new HashSet<>();
        // 起点入set集合
        set.add(veVertex);
        // 队列不为空 继续遍历
        while (!queue.isEmpty()) {
            // 出队获得节点
            Vertex<V, E> vertex = queue.poll();
            // 访问控制器执行任务
            if (visitor.visit(veVertex.value)) return;
            // 遍历当前节点的出度的所有边
            vertex.outEdge.forEach(item -> {
                // 如果set没有保存这个出度对应的in节点
                if (!set.contains(item.to)) {
                    // 当前入度入队
                    queue.offer(item.to);
                    // set添加当前节点
                    set.add(item.to);
                }
            });
        }
    }

    /**
     * 深度优先搜索
     *
     * @param begin   起点
     * @param visitor 访问任务调度器
     */
    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        // 起点不能为空
        if (visitor == null) return;
        // 全局获取到起点的节点信息
        Vertex<V, E> vertex = vertices.get(begin);
        // 如果全局没有这个起点的信息 return
        if (vertex == null) return;
        // 栈
        Stack<Vertex<V, E>> stack = new Stack<>();
        // 去重
        Set<Vertex<V, E>> set = new HashSet<>();
        // 添加起点
        stack.push(vertex);
        // 添加起点
        set.add(vertex);
        // 栈内还有元素则继续遍历
        while (!stack.isEmpty()) {
            // 弹出一个顶点
            Vertex<V, E> veVertex = stack.pop();
            // 访问控制器执行操作
            if (visitor.visit(veVertex.value)) return;
            // 遍历出度边集合
            veVertex.outEdge.forEach(item -> {
                // 如果set中没有这个元素添加成功 return true
                if (set.add(item.to)) {
                    // 栈添加这个元素到栈顶
                    stack.push(item.to);
                }
            });
        }
    }

    /**
     * 获取所有边的数量
     *
     * @return 数量
     */
    @Override
    public int edgesSize() {
        return edges.size();
    }

    /**
     * 获取所有节点的长度
     *
     * @return 长度
     */
    @Override
    public int vertices() {
        return vertices.size();
    }

    /**
     * 添加节点
     *
     * @param v 节点值
     */
    @Override
    public void addVerText(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    /**
     * 添加无权边
     *
     * @param from from节点
     * @param to   to节点
     */
    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    /**
     * 有权边
     *
     * @param from   from节点
     * @param to     to节点
     * @param weight 权值
     */
    @Override
    public void addEdge(V from, V to, E weight) {
        // 判断顶点是否存在
        Vertex<V, E> fromV = vertices.get(from);
        if (fromV == null) {
            // 顶点不存在创建一个
            fromV = new Vertex<>(from);
            // 放进区
            vertices.put(from, fromV);
        }
        // 同上
        Vertex<V, E> toV = vertices.get(to);
        if (toV == null) {
            toV = new Vertex<>(to);
            vertices.put(to, toV);
        }
        // 创建一个边
        Edge<V, E> edge = new Edge<>(fromV, toV);
        edge.weight = weight;
        // 判断边是否存在  存在的话remove会直接返回true
        if (fromV.outEdge.remove(edge)) {
            toV.inEdge.remove(edge);
            edges.remove(edge);
        }
        // 把新的加进去
        fromV.outEdge.add(edge);
        toV.inEdge.add(edge);
        edges.add(edge);
    }

    /**
     * 删除顶点
     *
     * @param v 被删除的顶点值
     */
    @Override
    public void removeVerText(V v) {
        // 删除顶点 从map删除返回的就是删除的顶点
        Vertex<V, E> vertex = vertices.remove(v);
        // 如果顶点位空 直接return
        if (vertex == null) return;
        // 遍历这个顶点的所有边
        for (Iterator<Edge<V, E>> iterator = vertex.outEdge.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            // 把这个顶点出度对应的入度顶点全部的入度集合更新
            edge.to.inEdge.remove(edge);
            iterator.remove();
            // 全局更新
            edges.remove(edge);
        }
        // 同上
        for (Iterator<Edge<V, E>> iterator = vertex.inEdge.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdge.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
    }

    /**
     * 求图的最小生成树
     *
     * @return 最小生成树包含的边集合
     */
    @Override
    public Set<EdgeInfo<V, E>> mst() {
        // 两个算法
        return kruskal();
        //return prim();
    }

    /**
     * prim算法
     *
     * @return 最小生成树
     */
    public Set<EdgeInfo<V, E>> prim() {
        // 得到所有节点的值
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        // 如果这个迭代器以开始就是空的 那么就直接异常
        if (!iterator.hasNext()) throw new RuntimeException("nothing...");
        // 获取到下一个节点
        Vertex<V, E> next = iterator.next();
        // 创建节点set去重
        Set<Vertex<V, E>> addedVertex = new HashSet<>();
        // 添加到set
        addedVertex.add(next);
        // 创建一个最小生成边的set集合
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // 小根堆
        MinHeap<Edge<V, E>> heap = new MinHeap<>(next.outEdge, comparator);
        // 最小数的边数有且只有节点数减一条
        int size = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < size) {
            // 权值最小的边
            Edge<V, E> edge = heap.remove();
            // 如果已经添加过这条边 直接下一次循环
            if (addedVertex.contains(edge.to)) continue;
            // 获取一个edgeInfo并把它添加到set中
            edgeInfos.add(edge.info());
            // 把当前to放进set中
            addedVertex.add(edge.to);
            // 把当前to节点的所有出度的边放进队中 更新堆
            heap.addAll(edge.to.outEdge);
        }
        return edgeInfos;
    }

    /**
     * kruskal算法
     *
     * @return 最小生成树
     */
    private Set<EdgeInfo<V, E>> kruskal() {
        // 图为空异常
        if (vertices.size() == 0) throw new RuntimeException("nothing...");
        // 最小生成树的边集
        HashSet<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // 小根堆
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, comparator);
        // 判断这个边是否会形成环 标记 并查集
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        // 遍历所有节点
        vertices.forEach((k, v) -> {
            // 各自为根节点
            uf.makeSet(v);
        });
        // 生成树的边只有节点数-1条
        while (!heap.isEmpty() && edgeInfos.size() < vertices.size() - 1) {
            // 小根堆头节点弹出  标记 每次都是弹出现存的所有边中最小权值的边
            Edge<V, E> edge = heap.remove();
            // 如果两个节点属于同一个集合 结束本次循环
            if (uf.isSame(edge.from, edge.to)) continue;
            // 在生成树的边集添加这条边
            edgeInfos.add(edge.info());
            // 合并这两个节点
            uf.union(edge.from, edge.to);
        }
        // 返回生成树
        return edgeInfos;
    }

    /**
     * 删除边
     *
     * @param from from-to的边删除
     * @param to   from-to的边删除
     */
    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromV = vertices.get(from);
        if (fromV == null) return;
        Vertex<V, E> toV = vertices.get(to);
        if (toV == null) return;
        Edge<V, E> edge = new Edge<>(fromV, toV);
        if (fromV.outEdge.remove(edge)) {
            toV.inEdge.remove(edge);
            edges.remove(edge);
        }
    }

    /**
     * 顶点类
     *
     * @param <V> 顶点的值类型
     * @param <E> 边的值类型
     */
    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdge = new HashSet<>();
        Set<Edge<V, E>> outEdge = new HashSet<>();

        public Vertex(V v) {
            this.value = v;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }

        /**
         * 重写equal方法
         *
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            return value == ((Vertex<V, E>) obj).value;
        }

        /**
         * 重写hashcode方法
         *
         * @return
         */
        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }
    }

    /**
     * 边的类
     *
     * @param <V> 顶点的值类型
     * @param <E> 边的值类型
     */
    static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        // 权值
        E weight;

        /**
         * 把当前的边对象转换成一个最小生成树的边对象
         *
         * @return 最小生成树的边对象
         */
        public EdgeInfo<V, E> info() {
            return new EdgeInfo<V, E>(from.value, to.value, weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        /**
         * 重写equal方法
         *
         * @param obj 对象
         * @return 和当前对象比较是否相同
         */
        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>) obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        /**
         * 重写hashcode方法
         *
         * @return
         */
        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }
    }
}