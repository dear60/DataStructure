package com.mingful.www.algorithm.shortestpathproblem;

import com.mingful.www.datastructure.graph.adjacencymatrixgraph.AdjacencyMatrixGraph;

import java.util.Arrays;

/**
 * @author fmf
 * @version 1.0
 * @className DijkstraTester
 * @description 最短路径问题 - 迪杰斯特拉(dijkstra)算法
 * 强调的是从一个点出发到其他各个顶点的最短路径(贪心法)
 * @create 2020-01-16 14:09
 **/
public class DijkstraTester {
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

        // 从顶点A出发，得出到各个顶点最小的路径
        System.out.println("------------------------------");
        Dijkstra.method(graph.getEdges(), vertexes, 0);
    }
}

class Dijkstra {

    /**
     * 迪杰斯特拉算法，核心思想就是每一轮选取一个最小值作为访问顶点，从该点出发到达另一个顶点总是最小的，
     * 类似三角形，两边之和总是大于第三条边，也就是从访问顶点到下一条边的权值(广度优先遍历)
     *
     * @param edges    图的邻接矩阵
     * @param vertexes 顶点对应的字符数组
     * @param index    从哪个顶点开始
     */
    public static void method(int[][] edges, String[] vertexes, int index) {

        // 记录从源出发点到各个顶点的距离，即最小权值
        int[] distance = new int[vertexes.length];
        // 记录顶点是否被访问过
        boolean[] isVisited = new boolean[vertexes.length];
        // 将源出发点置为已访问
        isVisited[index] = true;

        int minIndex = index;
        // 因为不用求自己到自己的距离，所以只需要循环vertexes.length - 1次即可
        for (int i = 1; i < vertexes.length; i++) {
            // 将minIndex做为访问起点对distance进行赋值
            setDistance(edges, distance, isVisited, minIndex);
            // 求出下一个访问结点的下标，即未被访问顶点的最小权值下标
            minIndex = getMinIndex(distance, isVisited);
            // 将当前顶点置为已访问
            isVisited[minIndex] = true;
        }
        System.out.println(Arrays.toString(distance));


    }

    /**
     * 将下标为index的顶点作为访问顶点，经行一轮最小权值赋值
     *
     * @param edges     图的邻接矩阵
     * @param distance  从起点到各个顶点最小权值数组
     * @param isVisited 顶点访问状态数组
     * @param index     访问顶点
     */
    public static void setDistance(int[][] edges, int[] distance, boolean[] isVisited, int index) {
        // 临时变量
        int temp;
        for (int i = 0; i < edges[index].length; i++) {
            //保证该点没被访问且从访问结点能到该顶点
            if (!isVisited[i] && edges[index][i] != 0) {
                // 记录从访问结点到该顶点的权值与源出发点到访问结点的最小权值的和
                temp = edges[index][i] + distance[index];
                // 如果是第一次赋值，或者赋值前的值大于该值，则将更新distance的值
                if (distance[i] == 0 || distance[i] > temp) {
                    distance[i] = temp;
                }
            }
        }
    }

    /**
     * 获取未被访问顶点最小权值的下标
     *
     * @param distance  从起点到各个顶点最小权值数组
     * @param isVisited 顶点访问状态数组
     * @return 下标
     */
    public static int getMinIndex(int[] distance, boolean[] isVisited) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < distance.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            if (distance[i] != 0 && min > distance[i]) {
                min = distance[i];
                index = i;
            }
        }
        return index;
    }


}
