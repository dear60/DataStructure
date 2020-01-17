package com.mingful.www.algorithm.minspanningtree;

import com.mingful.www.datastructure.graph.adjacencymatrixgraph.AdjacencyMatrixGraph;

import java.util.*;

/**
 * @author fmf
 * @version 1.0
 * @className KruskalTester
 * @description 最小生成树 - 克鲁斯卡尔(Kruskal)算法 - 贪心法
 * @create 2020-01-15 16:42
 **/
public class KruskalTester {
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

        Kruskal.method(graph.getEdges(), vertexes);
    }
}

class Kruskal {
    /**
     * 克鲁斯卡尔算法
     *
     * @param edges    图的邻接矩阵
     * @param vertexes 顶点对应的字符数组
     */
    public static void method(int[][] edges, String[] vertexes) {
        // 用于保存已有最小生成树中每个顶点的在最小生成树中的终点
        int[] ends = new int[vertexes.length];

        List<EdgeData> list = new ArrayList<>();
        // 添加边的数据，由于是无向图，取上三角
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges[0].length; j++) {
                if (edges[i][j] != 0) {
                    list.add(new EdgeData(vertexes[i], vertexes[j], edges[i][j]));
                }
            }
        }
        // 排序实现了Comparable接口
        Collections.sort(list);
        System.out.println("按权值排序后的边集合:" + list);

        // 按权值从小到大取出边
        for (EdgeData edgeData : list) {
            // 该边的起始下标
            int p1 = getPosition(edgeData.getBegin(), vertexes);
            // 该边的终止下标
            int p2 = getPosition(edgeData.getEnd(), vertexes);
            // p1的终点下标
            int m = getEndIndex(ends, p1);
            // p2的终点下标
            int n = getEndIndex(ends, p2);
            // 若该边的起点的终点和该边的终点的终点都指向同一个点，说明构成了回路，否则加入该边
            if (m != n) {
                // 将起点的终点指向终点的终点
                ends[m] = n;
                System.out.println("边<" + edgeData.getBegin() + "," + edgeData.getEnd() + "> 权值:" + edgeData.getValue());
            }
            //System.out.println("各个顶点的终点:" + Arrays.toString(ends));
        }
    }

    /**
     * 判断一个顶点的终点的下标
     *
     * @param ends 各个顶点的终点下标数组
     * @param i    顶点下标值
     * @return 终点下标
     */
    public static int getEndIndex(int[] ends, int i) {
        // 通过不断的的循环判断终点，直到该点时最终的终点
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 获取顶点字符对应的数组下标
     *
     * @param s        顶点字符
     * @param vertexes 顶点数组
     * @return 下标
     */
    public static int getPosition(String s, String[] vertexes) {
        for (int i = 0; i < vertexes.length; i++) {
            if (s != null && s.equals(vertexes[i])) {
                return i;
            }
        }
        return -1;
    }
}

/**
 * 边的数据结构
 */
class EdgeData implements Comparable<EdgeData> {

    private String begin;
    private String end;
    private Integer value;

    public EdgeData(String begin, String end, Integer value) {
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EdgeData{" +
                "begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(EdgeData o) {
        return value - o.value;
    }
}
