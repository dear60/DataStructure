package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className HeapSortTester
 * @description 堆排序
 * @create 2020-01-03 9:51
 **/
public class HeapSortTester {
    public static void main(String[] args) {
        int[] array = {3, 9, -2, 10, 20, 6, 7};
        System.out.println("排序前:" + Arrays.toString(array));
        long start = System.currentTimeMillis();
        HeapSort.sort(array);
        long end = System.currentTimeMillis();
        System.out.println("排序后:" + Arrays.toString(array));
        System.out.println("花费时间:" + (end - start));

        int[] test = new int[800000];
        Random random = new Random();
        for (int i = 0; i < 800000; i++) {
            test[i] = random.nextInt();
        }
        long startTest = System.currentTimeMillis();
        HeapSort.sort(test);
        long endTest = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTest - startTest));

    }
}

class HeapSort {
    public static void sort(int[] array) {
        int temp;
        //  从倒数第二层最后一个非叶子结点开始堆排序
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        System.out.println("=========================================");
        // 交换根结点和最后一个未排序结点，接着进行堆排序
        for (int i = array.length - 1; i >= 0; i--) {
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, i);
        }

    }

    private static void adjustHeap(int[] array, int index, int length) {
        int temp = array[index];
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            // 比较左右结点
            if (i + 1 < length && array[i] < array[i + 1]) {
                i++;
            }
            // 较大值和父节点比较
            if (array[i] > temp) {
                array[index] = array[i];
                index = i;
            } else {
                break;
            }
        }
        array[index] = temp;
        System.out.println(Arrays.toString(array));
    }
}
