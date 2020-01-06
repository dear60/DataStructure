package com.mingful.www.datastructure.queue.circlearrayqueue;

import java.util.Scanner;

/**
 * @author fmf
 * @version 1.0
 * @className CircleArrayQueueTester
 * @description 环形队列
 * @create 2019-12-11 11:13
 **/
public class CircleArrayQueueTester {

    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头部数据");

            key = scanner.next().charAt(0);

            switch (key) {
                default:
                    System.out.println("请输入正确的字符!");
                    break;
                case 's' :
                    queue.showQueue();
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;
                case 'a' :
                    System.out.print("输入一个数:");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g' :
                    System.out.println("取出的数据是" + queue.getQueue());
                    break;
                case 'h' :
                    System.out.println("队列头的数据是" + queue.headQueue());
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}


class CircleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，不能加入！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取出！");
            return -1;
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("没有数据！");
            return;
        }
        for (int i = front;i < front + size();i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            System.out.println("没有数据！");
            return -1;
        }
        return arr[front];
    }

}
