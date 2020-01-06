package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className InsertSortTester
 * @description 插入排序
 * @create 2019-12-23 15:06
 **/
public class InsertSortTester {

    public static void main(String[] args) {

        int[] array = {3, 9, -2, 10, 20};
        System.out.println("排序前:" + Arrays.toString(array));
        long start = System.currentTimeMillis();
        InsertSort.sort(array);
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

class InsertSort {


    public static void sort(int[] array) {

        int insertValue;
        int insertIndex;
        // 将数组分成已排序和排序两部分，取出未排序部分的第一个元素，与已排序比，直到找到合适的位置插入
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i;
            while (insertIndex > 0 && insertValue < array[insertIndex - 1]) {
                array[insertIndex] = array[insertIndex - 1];
                insertIndex--;
            }
            array[insertIndex] = insertValue;
        }
    }

}
