package com.mingful.www.algorithm.minspanningtree;

import com.mingful.www.datastructure.graph.adjacencymatrixgraph.AdjacencyMatrixGraph;

import java.util.HashSet;

/**
 * @author fmf
 * @version 1.0
 * @className PrimTester
 * @description 最小生成树 - 普里姆(Prim)算法 - 贪心法
 * @create 2020-01-15 14:51
 **/
public class PrimTester {

    public static void main(String[] args) {
        int n = 7;
        // 顶点集合
        String[] vertexes = {"A", "B", "C", "D", "E", "F", "G"};
        // 构建图
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
        long start = System.currentTimeMillis();
        Prim.method(graph.getEdges(), vertexes, 0);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("------------------------------");
        long start1 = System.currentTimeMillis();
        Prim.method1(graph.getEdges(), vertexes, 0);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }
}

class Prim {


    /**
     * 普里姆算法，核心思想是从已访问的点集合出发，找出到各个未被访问点的最小权值，把该点作为已被访问，进行循环
     *
     * @param edges    各个顶点的邻接矩阵
     * @param vertexes 顶点数组
     * @param vertex   从哪个顶点开始
     */
    public static void method(int[][] edges, String[] vertexes, int vertex) {
        // 记录判断次数
        int count = 0;
        // 记录顶点是否被访问
        boolean[] isVisited = new boolean[vertexes.length];
        // 将第一个顶点置为访问状态
        isVisited[vertex] = true;
        // 记录最小权值的起点下标
        int start = -1;
        // 记录最小权值的终点下标
        int end = -1;
        int min = Integer.MAX_VALUE;
        // vertexes个顶点，共需vertexes - 1条边，故进行vertexes - 1循环
        for (int k = 1; k < vertexes.length; k++) {
            // 遍历二维数组，
            // 若起点不是被访问的，过滤
            // 若终点是被访问过的，过滤
            // 若两点没有联系的，过滤
            // 在剩下的里面找出最小权值，记录，并将该点置为已访问
            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges[0].length; j++) {
                    count++;
                    if (isVisited[i] && !isVisited[j] && edges[i][j] != 0 && edges[i][j] < min) {
                        min = edges[i][j];
                        start = i;
                        end = j;

                    }
                }
            }
            System.out.println("边<" + vertexes[start] + "," + vertexes[end] + "> 权值:" + min);
            isVisited[end] = true;
            min = Integer.MAX_VALUE;
        }
        System.out.println("比较次数:" + count);
    }

    /**
     * 图的最小路径(最小生成树)
     *
     * @param edges    各个顶点的邻接矩阵
     * @param vertexes 顶点数组
     * @param vertex   从哪个顶点开始
     */
    public static void method1(int[][] edges, String[] vertexes, int vertex) {
        // 记录判断次数
        int count = 0;
        // 存以访问的点
        HashSet<Integer> set = new HashSet<>();
        // 将第一个顶点放入集合中
        set.add(vertex);
        // 记录最小权值的下标1
        int start = -1;
        // 记录最小权值的下标2
        int end = -1;
        int min = Integer.MAX_VALUE;
        // 和method比较，只是不遍历整个二维数组，而是将以访问过的顶点进行遍历，减少判断次数
        while (set.size() != vertexes.length) {
            for (int i : set) {
                for (int j = 0; j < edges[i].length; j++) {
                    count++;
                    if (set.contains(i) && !set.contains(j) && edges[i][j] != 0 && edges[i][j] < min) {
                        min = edges[i][j];
                        start = i;
                        end = j;
                    }
                }
            }
            System.out.println("边<" + vertexes[start] + "," + vertexes[end] + "> 权值:" + min);
            set.add(end);
            min = Integer.MAX_VALUE;
        }
        System.out.println("比较次数:" + count);
    }
}

