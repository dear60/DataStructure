package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className MergeSort
 * @description 归并排序
 * @create 2019-12-25 15:07
 **/
public class MergeSortTester {

    public static void main(String[] args) {

        //int[] array = {3, 3, -2, 3, 20};
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //MergeSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(MergeSort.sort(array)));

        int[] test = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            test[i] = random.nextInt();
        }

        long startTest = System.currentTimeMillis();
        MergeSort.sort(test);
        long endTest = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTest - startTest));
    }
}

class MergeSort {


    public static int[] sort(int[] array) {

        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int head, int tail) {

        if (head == tail) {
            return new int[]{array[head]};
        }
        int mid = (head + tail) / 2;

        // 先分后治
        int[] left = sort(array, head, mid);
        int[] right = sort(array, mid + 1, tail);

        int[] newArray = new int[left.length + right.length];

        int m = 0, i = 0, j = 0;
        // 比较左有序和右有序，小的存在newNum中
        while (i < left.length && j < right.length) {
            newArray[m++] = left[i] < right[j] ? left[i++] : right[j++];
        }
        // 将剩余的未选择左有序数组复制到newNum中
        while (i < left.length) {
            newArray[m++] = left[i++];
        }
        // 将剩余的未选择右有序数组复制到newNum中
        while (j < right.length) {
            newArray[m++] = right[j++];
        }
        return newArray;
    }
}
