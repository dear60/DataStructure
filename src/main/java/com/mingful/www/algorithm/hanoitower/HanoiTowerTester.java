package com.mingful.www.algorithm.hanoitower;

/**
 * @author fmf
 * @version 1.0
 * @className HanoiTowerTester
 * @description 汉诺塔
 * @create 2020-01-14 10:06
 **/
public class HanoiTowerTester {

    public static void main(String[] args) {
        HanoiTower.method(2, 'A', 'B', 'C');
    }
}

class HanoiTower {

    public static void method(int num, char a, char b, char c) {

        // 核心思想为分治法，即
        // 想处理 num     盘，就先处理 num - 1 盘，
        // 想处理 num - 1 盘，就先处理 num - 2 盘，
        // 想处理 num - 2 盘，就先处理 num - 3 盘，
        // ......
        if (num == 1) {
            System.out.println("第1个盘从" + a + " -> " + c);
        } else {
            // 先把num盘上面的所有盘从A移动到B，移动过程会用到C
            method(num - 1, a, c, b);
            // 把num盘从A移动到C
            System.out.println("第" + num + "个盘从" + a + " -> " + c);
            // 把num盘上面所有的盘从B移动到C，移动过程中会用到A
            method(num - 1, b, a, c);
        }
    }
}
