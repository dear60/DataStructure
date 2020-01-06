package com.mingful.www.datastructure.linkedlist.doublelinkedlist;

/**
 * @author fmf
 * @version 1.0
 * @className DoubleLinkedListTester
 * @description 双向链表
 * @create 2019-12-13 11:13
 **/
public class DoubleLinkedListTester {

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1, "赵");
        LinkedNode node2 = new LinkedNode(2, "钱");
        LinkedNode node3 = new LinkedNode(3, "孙");
        LinkedNode node4 = new LinkedNode(4, "李");

        DoubleLinkedList list = new DoubleLinkedList();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.list();

        list.update(new LinkedNode(3, "孙(修改)"));
        list.list();

        list.remove(4);
        list.list();

        System.out.println("========================");
        System.out.println(node1.pre);
        System.out.println(node1.next);
    }
}

class DoubleLinkedList {
    /**
     * 头结点
     */
    private LinkedNode head = new LinkedNode(0, "");

    /**
     * 从尾部添加结点
     *
     * @param node 结点
     */
    public void add(LinkedNode node) {
        LinkedNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    public LinkedNode getHead() {
        return head;
    }

    public void list() {
        LinkedNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void update(LinkedNode node) {
        LinkedNode temp = head.next;

        while (temp != null) {
            if (temp.id == node.id) {
                temp.name = node.name;
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到节点!");
    }

    public void remove(int i) {
        LinkedNode temp = head.next;

        while (temp != null) {
            if (temp.id == i) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            }
            temp = temp.next;
        }
    }
}


class LinkedNode {

    public int id;
    public String name;
    /**
     * 后继结点
     */
    public LinkedNode next;
    /**
     * 前驱结点
     */
    public LinkedNode pre;


    public LinkedNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
