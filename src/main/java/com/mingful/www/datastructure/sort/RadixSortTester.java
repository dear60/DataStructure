package com.mingful.www.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fmf
 * @version 1.0
 * @className RadixSortTester
 * @description 基数排序(桶排序),暂时不能取负数
 * @create 2019-12-25 17:02
 **/
public class RadixSortTester {

    public static void main(String[] args) {
        //int[] array = {53, 3, 542, 748, 14, 214, 154, 63, 616};
        int[] array = {8,9,1,7,2,3,5,4,6,0};
        RadixSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));

        int[] test = new int[800000];
        Random random = new Random();
        for (int i = 0;i < 800000;i++) {
            test[i] = Math.abs(random.nextInt());
        }

        long startTest = System.currentTimeMillis();
        RadixSort.sort(test);
        long endTest = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTest - startTest));
    }
}

class RadixSort {

    public static void sort (int[] array) {

        int max = array[0];
        for (int value : array) {
            if (max < value) {
                max = value;
            }
        }
        // 获得最大值的位数
        int maxDigit = ("" + max).length();

        int[][] radixArray = new int[10][array.length];
        int[] counter = new int[10];
        int bucket;
        // 通过比较各个数各个位的大小，从而进行排列
        for (int i = 0, n = 1; i < maxDigit; i++, n *= 10) {

            for (int j = 0; j < array.length; j++) {
                bucket =(array[j] / n) % 10;
                radixArray[bucket][counter[bucket]] = array[j];
                counter[bucket]++;
            }

            int pos = 0;
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < counter[x]; y++) {
                    array[pos++] = radixArray[x][y];
                    // radixArray[x][y] = 0;
                }
                counter[x] = 0;
            }
        }
    }
}
