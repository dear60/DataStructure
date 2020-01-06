package com.mingful.www.datastructure.search;

/**
 * @author fmf
 * @version 1.0
 * @className LinerSearchTester
 * @description 线性查找算法
 * @create 2019-12-27 11:20
 **/
public class LinerSearchTester {

    public static void main(String[] args) {
        int[] array = {1, 9, 11, -1, 34, 89};
        System.out.println(LinerSearch.search(array, -1));
    }
}
class LinerSearch {

    public static int search(int[] array, int value) {
        // 一个一个比对
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
