package com.mingful.www.datastructure.stack.arraystack;

import java.util.Scanner;

/**
 * @author fmf
 * @version 1.0
 * @className ArrayStackTester
 * @description 栈
 * @create 2019-12-18 9:47
 **/
public class ArrayStackTester {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        Scanner scanner = new Scanner(System.in);
        String key;
        boolean loop = true;
        while (loop) {
            System.out.println("show: 显示栈");
            System.out.println("push: 进栈");
            System.out.println("pop: 出栈");
            System.out.println("exit: 退出栈");
            System.out.println("请选择");
            key = scanner.next();

            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个整数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int temp = stack.pop();
                        System.out.println("出栈的值为" + temp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayStack {

    /**
     * 栈大小
     */
    private int maxSize;
    /**
     * 用数组模拟栈
     */
    private int[] array;
    /**
     * 栈顶指针
     */
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        top = -1;
    }

    /**
     * 是否栈满
     * @return boolean
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 是否栈空
     * @return boolean
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value 值
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        array[++top] = value;
    }

    /**
     * 出栈
     * @return int
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = array[top];
        top--;
        return value;
    }

    /**
     * 栈遍历
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top;i >= 0;i--) {
            System.out.println("array[" + i + "]=" + array[i]);
        }
    }
}
