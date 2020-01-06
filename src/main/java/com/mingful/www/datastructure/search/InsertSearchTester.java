package com.mingful.www.datastructure.search;

/**
 * @author fmf
 * @version 1.0
 * @className InsertSearchTester
 * @description 插值查找算法
 * @create 2019-12-27 14:17
 **/
public class InsertSearchTester {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(InsertSearch.search(array, 5));
    }
}
class InsertSearch {


    public static int search(int[] array, int value) {
        return search(array, 0, array.length - 1, value);
    }

    public static int search(int[] array, int left, int right, int value) {
        System.out.println("查找次数");
        if (left > right) {
            return -1;
        }
        // 算出该值更靠近左还是右
        int mid = left + (right - left) * (value - array[left]) / (array[right] - array[left]);
        if (array[mid] > value) {
            return search(array, left, mid - 1, value);
        } else if (array[mid] < value) {
            return search(array, mid + 1, right, value);
        } else {
            return mid;
        }
    }
}
