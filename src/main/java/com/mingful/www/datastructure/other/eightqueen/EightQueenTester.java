package com.mingful.www.datastructure.other.eightqueen;

/**
 * @author fmf
 * @version 1.0
 * @className EightQueenTester
 * @description 八皇后问题(回溯法)
 * @create 2019-12-20 15:39
 **/
public class EightQueenTester {

    public static void main(String[] args) {
        EightQueen queen = new EightQueen(8);
        queen.check(0);
        System.out.println("一共执行判断" + queen.getJudgeCount() + "次");
    }
}

class EightQueen {

    /**
     * 皇后个数
     */
    private int max;
    /**
     * 皇后位置数组(用一维数组代替二维数组)
     */
    private int[] array;

    /**
     * 解法(统计用)
     */
    private int count = 0;
    /**
     * 判断次数(统计用)
     */
    private int judgeCount = 0;

    public EightQueen(int max) {
        this.max = max;
        array = new int[max];
    }

    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        // 从第n行第一个位置开始放，随后第二个位置......
        for (int i = 0; i < max; i++) {
            array[n] = i;
            // 如果该位置能放，则放下一行的棋子
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            judgeCount++;
            // array[i] == array[n] 表示在同一列
            // Math.abs(n - i) == Math.abs(array[i] - array[n] 表示在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        count++;
        System.out.print("第" + count + "解法:");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public int getJudgeCount() {
        return judgeCount;
    }
}
