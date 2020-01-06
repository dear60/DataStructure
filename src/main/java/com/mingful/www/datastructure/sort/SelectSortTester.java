package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className SelectSortTester
 * @description 选择排序
 * @create 2019-12-23 11:21
 **/
public class SelectSortTester {
    public static void main(String[] args) {
        int[] array = {3, 9, -2, 10, 20};
        System.out.println("排序前:" + Arrays.toString(array));
        long start = System.currentTimeMillis();
        SelectSort.sort(array);
        long end = System.currentTimeMillis();
        System.out.println("排序后:" + Arrays.toString(array));
        System.out.println("花费时间:" + (end - start));

        int[] test = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            test[i] = random.nextInt();
        }

        int[] testCopy = test.clone();
        long startTest = System.currentTimeMillis();
        SelectSort.sort(test);
        long endTest = System.currentTimeMillis();
        BubbleSort.sort(testCopy);
        long endCopyTest = System.currentTimeMillis();
        System.out.println("冒泡花费时间:" + (endTest - startTest));
        System.out.println("选择花费时间:" + (endCopyTest - endTest));
    }
}

class SelectSort {
    public static void sort(int[] array) {
        int minIndex;
        int min;

        // 相较于冒泡排序，一趟只交换两个数，减少交换次数
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            min = array[minIndex];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
        }
    }
}