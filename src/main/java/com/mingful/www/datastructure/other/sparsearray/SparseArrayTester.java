package com.mingful.www.datastructure.other.sparsearray;

import java.io.*;

/**
 * @author fmf
 * @version 1.0
 * @className SparseArrayTester
 * @description 稀疏数组转换
 * @create 2019-12-10 8:54
 **/
public class SparseArrayTester {


    public static void main(String[] args) {
        int[][] chessBoard = new int[11][11];

        chessBoard[1][2] = 1;
        chessBoard[2][3] = 2;
        chessBoard[3][4] = 3;
        chessBoard[4][5] = 4;
        chessBoard[5][6] = 5;

        System.out.println("==================== 原始数组 start ====================");
        for (int[] two : chessBoard) {
            for (int one : two) {
                System.out.printf("%d\t", one);
            }
            System.out.println();
        }
        System.out.println("==================== 原始数组 end ====================");

        int count = 0;
        // 统计具体棋子的个数
        for (int[] two : chessBoard) {
            for (int one : two) {
                if (one != 0) {
                    count++;
                }
            }
        }
        int[][] sparseArray = new int[count + 1][3];
        // 为稀疏数组第一行赋值，即记录几行几列，棋子的个数
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = count;

        // 记录棋子的行列以及棋子的值，一个棋子为一行
        int index = 1;
        for (int i = 0; i < chessBoard[0].length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (chessBoard[i][j] != 0) {
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chessBoard[i][j];
                    index++;
                }
            }
        }


        System.out.println("==================== 稀疏数组 start ====================");
        for (int[] two : sparseArray) {
            for (int one : two) {
                System.out.printf("%d\t", one);
            }
            System.out.println();
        }
        System.out.println("==================== 稀疏数组 end ====================");

        String path = "./sparseArray.dat";
        PrintWriter out = null;
        try {
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));
            for (int[] two : sparseArray) {
                for (int one : two) {
                    out.print(one + " ");
                }
                out.println();
            }
            System.out.println("==================== 稀疏数组存盘成功 ====================");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

        BufferedReader in = null;
        int[][] restoreArray;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            int num = 0;
            String line;
            while (true) {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                num++;
            }
            restoreArray = new int[num][3];
            int r = 0;
            // 这里重新读取的原因在于in已经被污染，in.readLine()读不到内容了
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            while (true) {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(" ");
                for (int i = 0; i < temp.length; i++) {
                    restoreArray[r][i] = Integer.parseInt(temp[i]);
                }
                r++;
            }
            System.out.println("==================== 恢复稀疏数组 start ====================");
            for (int[] two : restoreArray) {
                for (int one : two) {
                    System.out.printf("%d\t", one);
                }
                System.out.println();
            }
            System.out.println("==================== 恢复稀疏数组 end====================");

            int[][] originArray = new int[restoreArray[0][0]][restoreArray[0][1]];

            for (int i = 1; i < restoreArray.length; i++) {
                originArray[restoreArray[i][0]][restoreArray[i][1]] = restoreArray[i][2];
            }

            System.out.println("==================== 恢复原始数组 start ====================");
            for (int[] two : originArray) {
                for (int one : two) {
                    System.out.printf("%d\t", one);
                }
                System.out.println();
            }
            System.out.println("==================== 恢复原始数组 end ====================");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
