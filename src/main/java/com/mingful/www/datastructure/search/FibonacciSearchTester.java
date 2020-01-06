package com.mingful.www.datastructure.search;

import java.util.Arrays;

/**
 * @author fmf
 * @version 1.0
 * @className FibonacciSearchTester
 * @description 斐波那契查找算法
 * @create 2019-12-27 14:31
 **/
public class FibonacciSearchTester {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(FibonacciSearch.search(array, 5));
    }
}
class FibonacciSearch {


    public static int search(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        int mid = 0;
        // 斐波那契数列下标
        int k = 0;
        int[] fibonacci = fibonacci(20);
        while (right > fibonacci[k] - 1) {
            k++;
        }
        // 构建新的长度为斐波那契数列大小的数组
        int[] newArray = Arrays.copyOf(array, fibonacci[k]);
        // 填充末尾值
        for (int i = right + 1; i < newArray.length; i++) {
            newArray[i] = newArray[right];
        }

        while (left <= right) {
            mid = left + fibonacci[k] - 1;
            System.out.println("mid = " + mid);
            // 黄金分割比例的数组下标
            if (newArray[mid] > value) {
                right = mid - 1;
                //k--;
                k -= 2;
            } else if (newArray[mid] < value) {
                left = mid + 1;
               //k -= 2;
                k--;
            } else {
                if (mid < right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }

    private static int[] fibonacci(int n) {
        int[] fibonacci = new int[n];
        fibonacci[0] = 1;
        fibonacci[1] = 2;

        for (int i = 2; i < n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }
}
