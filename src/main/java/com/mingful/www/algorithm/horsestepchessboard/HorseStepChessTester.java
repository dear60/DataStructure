package com.mingful.www.algorithm.horsestepchessboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author fmf
 * @version 1.0
 * @className HorseStepChessTester
 * @description 骑士周游问题 - 马踏棋盘算法
 * @create 2020-01-17 14:40
 **/
public class HorseStepChessTester {
    public static void main(String[] args) {
        // 初始化棋盘
        int[][] chessBoard = new int[8][8];
        HorseStepChess.show(chessBoard);
        System.out.println("------------------------------");
        // 定义从那个点开始遍历
        HorseStepChess.method(chessBoard, 0, 0);
        HorseStepChess.show(chessBoard);
    }
}

class HorseStepChess {

    /**
     * 棋盘的行数
     */
    private static int maxRow;

    /**
     * 棋盘的列数
     */
    private static int maxColumn;

    /**
     * 各个点的访问状态
     */
    private static boolean[][] isVisited;

    /**
     * 是否全部遍历
     */
    private static boolean finished = false;


    /**
     * 骑士周游问题 - 马踏棋盘算法
     *
     * @param chessBoard 棋盘数组
     * @param row        开始的行
     * @param column     开始的列
     */
    public static void method(int[][] chessBoard, int row, int column) {
        // 初始化各个值
        maxRow = chessBoard.length;
        maxColumn = chessBoard[0].length;
        isVisited = new boolean[maxRow][maxColumn];
        // 走下一步
        nextStep(chessBoard, row, column, 1);
    }

    /**
     * 设置马的下一步
     *
     * @param chessBoard 棋盘数组
     * @param row        行下标
     * @param column     列下标
     * @param count      第几步
     */
    public static void nextStep(int[][] chessBoard, int row, int column, int count) {
        // 设置当前点为第count步
        chessBoard[row][column] = count;
        // 设置当前点已被访问
        isVisited[row][column] = true;
        // 获取当前点的能访问的下一个点的集合
        ArrayList<Point> points = getNextPoints(new Point(row, column));
        // 此处使用了贪心法策略，求出当前点的下一个能走的点的集合的下一个能走的点的个数
        // 按照从小到大的顺序排列，以减少递归次数
        Collections.sort(points); // 可以试着注释掉这行经行对比
        Point point;
        while (!points.isEmpty()) {
            // 每次取出第一个元素，依次作为递归点
            point = points.remove(0);
            if (!isVisited[point.getX()][point.getY()]) {
                nextStep(chessBoard, point.getX(), point.getY(), count + 1);
            }
        }
        // 当无路可走时，判断是否全部走完，即点数是否等于二维数组大小，若没走完，则将之前修改的状态置为原来状态
        if (!finished && count < maxRow * maxColumn) {
            chessBoard[row][column] = 0;
            isVisited[row][column] = false;
        } else {
            // 当全部走完时，结束递归
            finished = true;
        }

    }

    /**
     * 获取当前点能访问下一个点的集合
     *
     * @param point 当前点
     * @return 当前点能访问的下一个点集合
     */
    public static ArrayList<Point> getNextPoints(Point point) {
        ArrayList<Point> points = new ArrayList<>();
        // 不要理解成x，y轴，而是二维数组的[][]
        int x = point.getX();
        int y = point.getY();
        // 按照马走日的规则，且所到的点还没被访问,如以P点为例

        //    0, 1, 2, 3, 4, 5, 6, 7
        // 0 [0, 0, 0, 0, 0, 0, 0, 0]
        // 1 [0, 0, 1, 0, 2, 0, 0, 0]
        // 2 [0, 8, 0, 0, 0, 3, 0, 0]
        // 3 [0, 0, 0, P, 0, 0, 0, 0]
        // 4 [0, 7, 0, 0, 0, 4, 0, 0]
        // 5 [0, 0, 6, 0, 5, 0, 0, 0]
        // 6 [0, 0, 0, 0, 0, 0, 0, 0]
        // 7 [0, 0, 0, 0, 0, 0, 0, 0]
        // 如上二维数组中的1点
        if (x - 2 >= 0 && y - 1 >= 0 && !isVisited[x - 2][y - 1]) {
            points.add(new Point(x - 2, y - 1));
        }
        // 如上二维数组中的2点
        if (x - 1 >= 0 && y - 2 >= 0 && !isVisited[x - 1][y - 2]) {
            points.add(new Point(x - 1, y - 2));
        }
        // 如上二维数组中的3点
        if (x + 1 < maxRow && y - 2 >= 0 && !isVisited[x + 1][y - 2]) {
            points.add(new Point(x + 1, y - 2));
        }
        // 如上二维数组中的4点
        if (x + 2 < maxRow && y - 1 >= 0 && !isVisited[x + 2][y - 1]) {
            points.add(new Point(x + 2, y - 1));
        }
        // 如上二维数组中的5点
        if (x + 2 < maxRow && y + 1 < maxColumn && !isVisited[x + 2][y + 1]) {
            points.add(new Point(x + 2, y + 1));
        }
        // 如上二维数组中的6点
        if (x + 1 < maxRow && y + 2 < maxColumn && !isVisited[x + 1][y + 2]) {
            points.add(new Point(x + 1, y + 2));
        }
        // 如上二维数组中的7点
        if (x - 1 >= 0 && y + 2 < maxColumn && !isVisited[x - 1][y + 2]) {
            points.add(new Point(x - 1, y + 2));
        }
        // 如上二维数组中的8点
        if (x - 2 >= 0 && y + 1 < maxColumn && !isVisited[x - 2][y + 1]) {
            points.add(new Point(x - 2, y + 1));
        }
        return points;
    }

    /**
     * 显示棋盘内容
     *
     * @param chessBoard 棋盘
     */
    public static void show(int[][] chessBoard) {
        for (int[] temp : chessBoard) {
            System.out.println(Arrays.toString(temp));
        }
    }
}

class Point implements Comparable<Point> {

    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        return getNextVisitedNum() - o.getNextVisitedNum();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNextVisitedNum() {
        return HorseStepChess.getNextPoints(this).size();
    }
}
