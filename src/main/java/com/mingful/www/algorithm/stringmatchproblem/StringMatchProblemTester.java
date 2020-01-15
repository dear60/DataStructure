package com.mingful.www.algorithm.stringmatchproblem;

import java.util.Arrays;

/**
 * @author fmf
 * @version 1.0
 * @className StringMatchProblemTester
 * @description 字符串匹配问题 -- kmp算法
 * @create 2020-01-14 15:13
 **/
public class StringMatchProblemTester {

    public static void main(String[] args) {
        String string = "BBC ABCDAB ABCDABCDABDE";
        String subString = "ABCDABD";

        System.out.println("暴力算法 " + StringMatchProblem.violent(string, subString));
        System.out.println("部分字符匹配数组 " + Arrays.toString(StringMatchProblem.getNext(subString)));
        System.out.println("kmp算法 " + StringMatchProblem.kmp(string, subString));
    }
}

class StringMatchProblem {


    /**
     * 字符串匹配朴素算法(暴力算法)
     *
     * @param s         被匹配字符
     * @param subString 待匹配字符
     * @return 被匹配字符的下标，若无匹配项，则返回 -1
     */
    public static int violent(String s, String subString) {
        // 被匹配字符的下标
        int i;
        // 待匹配字符的下标
        int j = 0;
        // 被匹配字符的滑动下标
        int temp;
        for (i = 0; i < s.length(); i++) {
            // 令滑动下标等于初始下标
            temp = i;
            // 防止下标越界
            while (j < subString.length() && temp < s.length()) {
                // 当有字符匹配时，比较下一个，否则将待匹配字符下标重置，且回到for循环
                if (s.charAt(temp) == subString.charAt(j)) {
                    temp++;
                    j++;
                } else {
                    j = 0;
                    break;
                }
            }
            // 当完全匹配时，返回被匹配字符下标
            if (j == subString.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 字符串匹配kmp算法
     *
     * @param s         被匹配字符
     * @param subString 待匹配字符
     * @return 被匹配字符的下标，若无匹配项，则返回 -1
     */
    public static int kmp(String s, String subString) {
        int[] next = getNext(subString);
        // 被匹配字符串依次进行，减少的是两个字符串的滑动次数，进一步减少重复操作
        for (int i = 0, j = 0; i < s.length(); i++) {
            // 字符不相等时，待匹配字符的指针滑动回上一个相等的下标处
            while (j > 0 && s.charAt(i) != subString.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == subString.charAt(j)) {
                j++;
            }
            if (j == subString.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取部分匹配值表
     *
     * @param subString 待匹配字符
     * @return 待匹配字符的部分匹配值表
     */
    public static int[] getNext(String subString) {

        int[] next = new int[subString.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < subString.length(); i++) {
            // 核心思想为将子字符串按一个一个字符进行前缀和后缀比较
            // 例如:ABCDABD
            // 当指针i指向第二个字符B的时候，
            // 前缀有:A    后缀有B    此时没有相等的字符
            // 当指针i指向第三个字符C的时候，
            // 前缀有:A,AB    后缀有BC,C    此时没有相等的字符
            // 当指针i指向第四个字符D的时候，
            // 前缀有:A,AB,ABC,    后缀有BCD,CD,D    此时没有相等的字符
            // 当指针i指向第五个字符A的时候，
            // 前缀有:A,AB,ABC,ABCD    后缀有BCDA,CDA,DA,A    此时有相等的字符A,长度为1，故next[4] = 1;
            // 当指针i指向第六个字符B的时候，
            // 前缀有:A,AB,ABC,ABCD,ABCDA    后缀有BCDAB,CDAB,DAB,AB,B    此时有相等的字符AB,长度为2，故next[5] = 2;
            // 当指针i指向第七个字符D的时候，
            // 前缀有:A,AB,ABC,ABCD,ABCDAB    后缀有BCDABD,CDABD,DABD,ABD,BD,D    此时没有相等的字符

            // KMP算法的精髓就在于当前后缀比较的时候，下一步的某些比较都是包含了上一步比较的操作，
            // 例如AB和BC比较就包含了A和B的比较
            // 例如ABC和BCD比较就包含了AB和BC的比较
            // 例如ABCD和BCDA比较就包含了ABC和BCD的比较
            // ......
            // 最大程度的减少了重复操作
            while (j > 0 && subString.charAt(i) != subString.charAt(j)) {
                // todo: 回退为什么是这个公式
                j = next[j - 1];
            }
            if (subString.charAt(i) == subString.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
