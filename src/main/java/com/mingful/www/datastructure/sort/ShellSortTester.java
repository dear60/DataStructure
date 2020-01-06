package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className ShellSortTester
 * @description 希尔排序
 * @create 2019-12-23 16:04
 **/
public class ShellSortTester {

    public static void main(String[] args) {

        //int[] array = {3, 9, -2, 10, 20};
        int[] array = {8,9,1,7,2,3,5,4,6,0};
        System.out.println("排序前:" + Arrays.toString(array));
        long start = System.currentTimeMillis();
        ShellSort.changeSort(array);
        long end = System.currentTimeMillis();
        System.out.println("排序后:" + Arrays.toString(array));
        System.out.println("花费时间:" + (end - start));


        int[] test = new int[800000];
        Random random = new Random();
        for (int i = 0;i < 800000;i++) {
            test[i] = random.nextInt();
        }

        long startTest = System.currentTimeMillis();
        ShellSort.moveSort(test);
        long endTest = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTest - startTest));

    }
}

class ShellSort {

    /**
     * 交换式,没什么用
     *
     * @param array 传入数组
     */
    public static void changeSort(int[] array) {
        int temp;
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j = j - gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 移位式
     *
     * @param array 传入数组
     */
    public static void moveSort(int[] array) {
        int j;
        int temp;
        // 相较于插入排序，增加了步长的概念，即在步长内经行插入排序，减少比较趟数以及交换次数
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < array.length; i++) {
                j = i;
                temp = array[j];
                while (j - gap >= 0 && temp < array[j - gap]) {
                    array[j] = array[j -gap];
                    j = j - gap;
                }
                array[j] = temp;
            }
        }
    }
}


