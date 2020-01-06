package com.mingful.www.datastructure.linkedlist.josephucircle;

/**
 * @author fmf
 * @version 1.0
 * @className JosephusCircleTester
 * @description 用单向环形链表表示约瑟夫环
 * @create 2019-12-17 16:30
 **/
public class JosephusCircleTester {

    public static void main(String[] args) {
        JosephusCircle circle = new JosephusCircle();

        circle.creat(6);
        circle.show();
        circle.count(3, 3);
    }
}

class JosephusCircle {

    private Boy first;

    public void creat(int number) {
        if (number < 1) {
            System.out.println("number不正确");
            return;
        }
        Boy temp = null;
        for (int i = 1; i <= number; i++) {
            Boy boy = new Boy(i);
            // 如果是第一个结点，直接头结点指向该结点即可
            // 如果不是，则尾部元素指向该结点，而该结点指向第一个结点
            if (i == 1) {
                first = boy;
                first.setNext(first);
                temp = first;
            } else {
                temp.setNext(boy);
                boy.setNext(first);
                temp = boy;
            }
        }
    }

    public void show() {
        Boy temp = first;
        while (true) {
            System.out.println("该小孩编号为:" + temp.getId());
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }
    }

    public void count(int start, int count) {
        Boy temp = first;
        // 找到指向头结点的结点
        while (temp.getNext() != first) {
            temp = temp.getNext();
        }
        // 同时移动start位置，即从start位置开始算
        for (int i = 1; i < start; i++) {
            first = first.getNext();
            temp = temp.getNext();
        }

        while (first.getNext() != first) {
            for (int i = 1; i < count; i++) {
                first = first.getNext();
                temp = temp.getNext();
            }
            System.out.println("编号为" + first.getId() + "的小孩出圈");
            first = first.getNext();
            temp.setNext(first);
        }
        System.out.println("最后一个小孩的编号为" + first.getId());
    }
}

class Boy {

    private Integer id;
    private Boy next;

    public Boy(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
