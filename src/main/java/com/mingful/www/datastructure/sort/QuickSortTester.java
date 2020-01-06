package com.mingful.www.datastructure.sort;

import java.util.*;

/**
 * @author fmf
 * @version 1.0
 * @className QuickSortTester
 * @description 快速排序
 * @create 2019-12-24 10:51
 **/
public class QuickSortTester {

    public static void main(String[] args) {

        int[] array = {3, 3, -2, 3, 20};
        //int[] array = {8,9,1,7,2,3,5,4,6,0};
        QuickSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));


        int[] test = new int[800000];
        Random random = new Random();
        for (int i = 0; i < 800000; i++) {
            test[i] = random.nextInt();
        }

        long startTest = System.currentTimeMillis();
        QuickSort.sort(test);
        long endTest = System.currentTimeMillis();
        System.out.println("花费时间:" + (endTest - startTest));

    }
}

class QuickSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);

    }

    private static void sort(int[] array, int left, int right) {

        int l = left;
        int r = right;
        // 基准元素 pivot
        int pivot = array[(left + right) / 2];
        int temp;

        while (l < r) {
            // 从左边找到一个比基准元素大的元素
            while (array[l] < pivot) {
                l++;
            }
            // 从右边找到一个比基准元素小的元素
            while (array[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            // 交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            // 若l等于基准元素的下标位置，r左移
            if (array[l] == pivot) {
                r--;
            }
            // 若r等于基准元素的下标位置，l右移
            if (array[r] == pivot) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            sort(array, left, r);
        }
        if (right > l) {
            sort(array, l, right);
        }
    }


    /**
     * 用栈代替递归
     *
     * @param arr        数组
     * @param startIndex 数组开始下标
     * @param endIndex   数组结束下标
     */
    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();
        // 整个数列的起止下标，以哈希的形式入栈
        Map<String, Integer> rootParam = new HashMap<>();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);
        // 循环结束条件：栈为空时结束
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准元素位置
            int pivotIndex = partition(arr, param.get("startIndex"), param.get("endIndex"));
            // 根据基准元素分成两部分, 把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<String, Integer>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<String, Integer>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            // 控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            // 控制right指针比较并右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }

            // 交换left和right指向的元素
            if (left < right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        // pivot和指针重合点交换
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;
        return left;
    }
}

