package com.mingful.www.algorithm.knapsackproblem;

import java.util.Arrays;

/**
 * @author fmf
 * @version 1.0
 * @className KnapsackProblemTester
 * @description 背包问题
 * @create 2020-01-14 10:50
 **/
public class KnapsackProblemTester {

    public static void main(String[] args) {
        int[] weight = {1, 4, 3};
        int[] value = {1500, 3000, 2000};
        int capacity = 4;
        int maxValue = KnapsackProblem.method(weight, value, capacity);
        System.out.println("背包的最大价值为 " + maxValue);
    }
}

class KnapsackProblem {

    /**
     * 动态规划法求背包问题最优解
     *
     * @param weight   物品的重量
     * @param value    物品的价值
     * @param capacity 背包的容量
     * @return 背包最大价值
     */
    public static int method(int[] weight, int[] value, int capacity) {
        // 保存在重量和价值条件下的背包最大价值
        int[][] maxValueArray = new int[value.length + 1][capacity + 1];
        // 行(i)表示物品价值，列(j)表示动态背包容量
        for (int i = 1; i < maxValueArray.length; i++) {
            for (int j = 1; j < maxValueArray[0].length; j++) {
                // 当装入的物品重量大于背包容量时取前一行的同容量的最大价值
                // 否则，将前一行的同容量的最大价值和该物品价值 + 前一行的剩余背包容量的最大价值之间取大值
                if (weight[i - 1] > j) {
                    maxValueArray[i][j] = maxValueArray[i - 1][j];
                } else {
                    maxValueArray[i][j] = Math.max(maxValueArray[i - 1][j], value[i - 1] + maxValueArray[i - 1][j - weight[i - 1]]);
                }
            }
        }
        for (int[] temp : maxValueArray) {
            System.out.println(Arrays.toString(temp));
        }
        return maxValueArray[value.length][capacity];
    }
}
