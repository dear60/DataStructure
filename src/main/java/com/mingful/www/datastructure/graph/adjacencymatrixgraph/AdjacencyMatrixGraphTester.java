package com.mingful.www.datastructure.graph.adjacencymatrixgraph;

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
        // 顶点集合
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 构建图
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

