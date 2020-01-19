package com.mingful.www.datastructure.search;

/**
 * @author fmf
 * @version 1.0
 * @className BinarySearchTester
 * @description 二分查找算法
 * @create 2019-12-27 11:25
 **/
public class BinarySearchTester {

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(BinarySearch.search(array, 5));
        System.out.println("--------------------------------");
        System.out.println(BinarySearch.search1(array, 5));
    }
}

class BinarySearch {

    /**
     * 二分查找算法
     *
     * @param array 有序数组
     * @param value 待查找的值
     * @return 若存在，返回该值的下标，若不存在，则返回-1
     */
    public static int search(int[] array, int value) {
        return search(array, 0, array.length - 1, value);
    }

    /**
     * 查找在左右指针区间的value下标
     *
     * @param array 有序数组
     * @param left  左指针
     * @param right 右指针
     * @param value 待查找的值
     * @return 若存在，返回该值的下标，若不存在，则返回-1
     */
    public static int search(int[] array, int left, int right, int value) {
        System.out.println("查找次数");
        if (left > right) {
            return -1;
        }
        // 找到中间点
        int mid = (left + right) / 2;
        if (array[mid] > value) { // 左 - 中间点
            return search(array, left, mid - 1, value);
        } else if (array[mid] < value) {  // 中间点 - 右
            return search(array, mid + 1, right, value);
        } else {
            return mid;
        }
    }

    /**
     * 采用非递归方式经行二分查找
     *
     * @param array 待查找的数组
     * @param value 查找的值
     * @return 该值的下标，若不存在，则返回 -1
     */
    public static int search1(int[] array, int value) {
        int left = 0;
        int right = array.length;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] > value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
