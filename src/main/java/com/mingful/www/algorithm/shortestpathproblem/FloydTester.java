package com.mingful.www.algorithm.shortestpathproblem;

import com.mingful.www.datastructure.graph.adjacencymatrixgraph.AdjacencyMatrixGraph;

import java.util.Arrays;

/**
 * @author fmf
 * @version 1.0
 * @className FloydTester
 * @description 最短路径问题 - 佛洛依德(floyd)算法
 * 强调的是求出各个顶点之间的最短路径(动态规划法)
 * @create 2020-01-17 9:02
 **/
public class FloydTester {
    public static void main(String[] args) {
        int n = 7;
        String[] vertexes = {"A", "B", "C", "D", "E", "F", "G"};
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }

        graph.insertEdges(0, 1, 5);
        graph.insertEdges(0, 2, 7);
        graph.insertEdges(0, 6, 2);
        graph.insertEdges(1, 3, 9);
        graph.insertEdges(1, 6, 3);
        graph.insertEdges(2, 4, 8);
        graph.insertEdges(3, 5, 4);
        graph.insertEdges(4, 5, 5);
        graph.insertEdges(4, 6, 4);
        graph.insertEdges(5, 6, 6);

        graph.showGraph();
        // 将没有直接关联的顶点权值从0置为65535
        int max = 65535;
        int[][] edges = graph.getEdges();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[0].length; j++) {
                if (edges[i][j] == 0 && i != j) {
                    edges[i][j] = max;
                }
            }
        }
        System.out.println("------------------------------");
        Floyd.method(graph.getEdges(), vertexes);
        graph.showGraph();
    }
}

class Floyd {
    public static void method(int[][] edges, String[] vertexes) {
        int length = vertexes.length;
        int[][] pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
        int len;
        // 对中间顶点的遍历，k就是中间顶点的下标
        for (int k = 0; k < length; k++) {
            // 对出发顶点的遍历，i就是中间顶点的下标
            for (int i = 0; i < length; i++) {
                // 对终点顶点的遍历，j就是终点顶点的下标
                for (int j = 0; j < length; j++) {
                    // 例如，k = 0，i = 1，j = 2,可得edges[i][j]是不可达的，即BC是没有直接关联的，此时可通过中间点k，即A，
                    // AB是可达的，AC是可达的，那么AB + AC就为BC的权值
                    // 又例如， k = 1， i = 2，j = 3，可得edges[i][j]是不可达的，即CD没有直接关联，此时可通过中间点k，即B，
                    // BC是可达的，BD是可达的，那么BC + BD就为CD的权值
                    len = edges[i][k] + edges[k][j];
                    // 如果通过中间顶点来访问起点到终点的权值小于他们直接访问的权值，那么则将次替换为最小权值
                    if (len < edges[i][j]) {
                        edges[i][j] = len;
                    }
                }
            }
        }
    }
}
