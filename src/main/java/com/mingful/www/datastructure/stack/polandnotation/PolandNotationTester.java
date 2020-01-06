package com.mingful.www.datastructure.stack.polandnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fmf
 * @version 1.0
 * @className PolandNotationTester
 * @description 中缀转后缀，逆波兰表达式
 * @create 2019-12-19 10:57
 **/
public class PolandNotationTester {

    public static void main(String[] args) {

        PolandNotation polandNotation = new PolandNotation();
        String expression = "12+((3456+7)*8)-910";
        // 中缀转List
        List<String> infixExpressionList = polandNotation.infixExpressionList(expression);
        System.out.println(infixExpressionList);

        // List转后缀
        List<String> parseSuffixExpressionList = polandNotation.parseSuffixExpressionList(infixExpressionList);
        System.out.println(parseSuffixExpressionList);

        System.out.println(expression + "=" + polandNotation.calculate(parseSuffixExpressionList));
    }
}


class PolandNotation {

    /**
     * 表达式转中缀表达式
     * @param expression 表达式
     * @return List<String>
     */
    public List<String> infixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        // 数字拼接
        String str = "";
        char[] array = expression.toCharArray();
        // 遍历数组，若不是数字，则加入到list中，否则进行拼接，直到遇见非数字
        for (char c : array) {
            if (c < 48 || c > 57) {
                if (!"".equals(str)) {
                    list.add(str);
                    str = "";
                }
                list.add(c + "");
            } else {
                str = str + c;
            }
        }
        list.add(str);
        return list;
    }

    /**
     * 解析中缀变成后缀
     * @param list 中缀表达式字符数组
     * @return List<String>
     */
    public List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> stack = new Stack<>();
        // 原本是双栈，但是character没有出栈操作，故用集合代替栈
        List<String> character = new ArrayList<>();

        // [10, +, (, (, 1232, +, 3, ), *, 4, ), -, 500]
        for (String s : list) {
            // 遍历字符集，
            // 1. 若是数字，则直接到字符集
            // 2. 若为'('或stack为空或stack栈顶为'('直接入栈
            // 3. 若为')'则将stack中的运算符出栈，直到遇到'('为止
            // 4. 若该运算符的优先级小于等于栈顶运算符优先级，则将栈顶运算符保存到字符集中
            if (s.matches("\\d+")) {
                character.add(s);
            } else if (stack.empty() || "(".equals(s) || "(".equals(stack.peek())) {
                stack.push(s);
            } else if (")".equals(s)) {
                while (!"(".equals(stack.peek())) {
                    character.add(stack.pop());
                }
                stack.pop();
            } else {
                while (stack.size() != 0 && Operation.getValue(s) <= Operation.getValue(stack.peek())) {
                    character.add(stack.pop());
                }
                stack.push(s);
            }
        }
        // 将剩余运算符依次存到字符集中
        while (stack.size() != 0) {
            character.add(stack.pop());
        }
        return character;
    }

    public int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int result = 0;
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if ("+".equals(s)) {
                    result = num1 + num2;
                } else if ("-".equals(s)) {
                    result = num1 - num2;
                } else if ("*".equals(s)) {
                    result = num1 * num2;
                } else if ("/".equals(s)) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符出错");
                }
                stack.push(result + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

}


/**
 * 运算符优先级
 */
class Operation {

    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DLV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+" :
                result = ADD;
                break;
            case "-" :
                result = SUB;
                break;
            case  "*" :
                result = MUL;
                break;
            case "/" :
                result = DLV;
                break;
            default :
                System.out.println("不存在该运算符");
                break;

        }
        return result;
    }
}