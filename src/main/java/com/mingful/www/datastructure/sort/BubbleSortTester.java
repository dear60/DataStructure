package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className BubbleSortTester
 * @description 冒泡排序
 * @create 2019-12-23 10:27
 **/
public class BubbleSortTester {

    public static void main(String[] args) {

        int[] array = {3, 9, -2, 10, 20};
        System.out.println("排序前:" + Arrays.toString(array));
        long start = System.currentTimeMillis();
        BubbleSort.sort(array);
        long end = System.currentTimeMillis();
        System.out.println("排序后:" + Arrays.toString(array));
        System.out.println("花费时间:" + (end - start));

        int[] test = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            test[i] = random.nextInt();
        }

        long startTest = System.currentTimeMillis();
        BubbleSort.sort(test);
        long endTest = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTest - startTest));
    }
}

class BubbleSort {

    public static void sort(int[] array) {

        boolean flag = false;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {

            for (int j = 0; j < array.length - 1 - j; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (flag) {
                flag = false;
            } else {
                break;
            }
        }
    }

}
