package com.mingful.www.datastructure.graph.adjacencymatrixgraph;

import java.util.*;

/**
 * @author fmf
 * @version 1.0
 * @className AdjacencyMatrixGraphTester
 * @description 图 -- 邻接矩阵
 * @create 2020-01-13 9:48
 **/
public class AdjacencyMatrixGraphTester {

    public static void main(String[] args) {
//        int n = 5;
//        String[] vertexes = {"A", "B", "C", "D", "E"};
        int n = 8;
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }

//        graph.insertEdges(0, 1, 1);
//        graph.insertEdges(0, 2, 1);
//        graph.insertEdges(1, 2, 1);
//        graph.insertEdges(1, 3, 1);
//        graph.insertEdges(1, 4, 1);

        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);

        graph.showGraph();
        graph.dfs(0);
        System.out.println(graph.getDfsList());
        System.out.println("--------------------------------");
        graph.bfs(0);
        System.out.println(graph.getBfsList());
    }
}

class AdjacencyMatrixGraph {

    /**
     * 顶点集合
     */
    private ArrayList<String> vertexList;
    /**
     * 邻接矩阵
     */
    private int[][] edges;
    /**
     * 边的个数
     */
    private int numberOfEdge;

    /**
     * 深度优先遍历顶点集合
     */
    private List<String> dfsList = new ArrayList<>();

    /**
     * 广度优先遍历顶点集合
     */
    private List<String> bfsList = new ArrayList<>();

    public AdjacencyMatrixGraph(int n) {

        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numberOfEdge = 0;
    }

    /**
     * 添加顶点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 返回顶点个数
     *
     * @return int
     */
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    /**
     * 添加顶点与顶点之间的关系
     *
     * @param vertex1Index 顶点1的下标
     * @param vertex2Index 顶点2的下标
     * @param weight       权值
     */
    public void insertEdges(int vertex1Index, int vertex2Index, int weight) {
        edges[vertex1Index][vertex2Index] = weight;
        // 当做无向图来处理
        edges[vertex2Index][vertex1Index] = weight;
        // 边的条数加一
        numberOfEdge++;
    }

    /**
     * 返回边的条数
     *
     * @return int
     */
    public int getNumberOfEdge() {
        return this.numberOfEdge;
    }

    /**
     * 返回特定下标的顶点
     *
     * @param i 下标
     * @return 顶点
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回顶点1和顶点2的权值
     *
     * @param vertex1Index 顶点1
     * @param vertex2Index 顶点2
     * @return 权值
     */
    public int getWeight(int vertex1Index, int vertex2Index) {
        return edges[vertex1Index][vertex2Index];
    }

    /**
     * 显示邻接矩阵
     */
    public void showGraph() {
        for (int[] array : edges) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 深度优先遍历(depth first search)
     *
     * @param vertex 以深度优先的下一个顶点
     */
    public void dfs(int vertex) {
        // 将进入到该方法的顶点添加到集合中
        dfsList.add(getValueByIndex(vertex));
        System.out.println(dfsList);
        // 以递归的方式遍历，若该顶点和下一个顶点有关系，且下一个顶点不在集合中，则进入dfs方法中
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.println("vertex = " + vertex + ", i = " + i);
            if (!dfsList.contains(getValueByIndex(i)) && edges[vertex][i] != 0) {
                dfs(i);
            }
        }
    }

    /**
     * 广度优先遍历(broad first search)
     *
     * @param vertex 顶点
     */
    public void bfs(int vertex) {
        // 保存同一层的结点，用linkedList的原因是保证顺序,其次是有像队列一样添加到尾部(addLast)和取出头部(removeFirst)的操作
        LinkedList<Integer> nextVertex = new LinkedList<>();
        // 添加头结点
        nextVertex.addLast(vertex);
        // 构造第一层，即头结点的LinkedList
        bfsList.add(getValueByIndex(vertex));
        bfs(nextVertex);
    }

    /**
     * 一层一层构造LinkedList
     *
     * @param nextVertex 存放同一层结点的集合
     */
    private void bfs(LinkedList<Integer> nextVertex) {
        LinkedList<Integer> temp = new LinkedList<>();
        // 如果集合为空，则遍历完毕
        if (nextVertex == null || nextVertex.size() == 0) {
            return;
        }
        // 遍历集合，将处于同一层的结点存起来，以当作下次遍历用的集合
        while (!nextVertex.isEmpty()) {
            int index = nextVertex.removeFirst();
            for (int i = 0; i < vertexList.size(); i++) {
                System.out.println("index = " + index + ", i = " + i);
                if (!bfsList.contains(getValueByIndex(i)) && edges[index][i] != 0) {
                    temp.addLast(i);
                    bfsList.add(getValueByIndex(i));
                }
            }
        }
        bfs(temp);
    }

    public List<String> getDfsList() {
        return dfsList;
    }

    public List<String> getBfsList() {
        return bfsList;
    }
}
