package com.mingful.www.datastructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fmf
 * @version 1.0
 * @className HuffmanTreeTester
 * @description 数组转成赫夫曼树
 * @create 2020-01-06 15:01
 **/
public class HuffmanTreeTester {

    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree(array);
        Node root = huffmanTree.getRootFromArray();
        huffmanTree.preTraversal(root);
    }
}

class HuffmanTree {

    private int[] array;

    public HuffmanTree(int[] array) {
        this.array = array;
    }

    public Node getRootFromArray() {
        List<Node> list = new ArrayList<>();
        for (int value : array) {
            list.add(new Node(value));
        }

        Node leftNode;
        Node rightNode;
        Node parent;
        while (list.size() > 1) {
            Collections.sort(list);
            leftNode = list.get(0);
            rightNode = list.get(1);

            parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);

    }

    /**
     * 先序遍历
     *
     * @param root 根节点
     */
    public void preTraversal(Node root) {

        System.out.println(root);
        if (root.getLeft() != null) {
            preTraversal(root.getLeft());
        }
        if (root.getRight() != null) {
            preTraversal(root.getRight());
        }
    }
}

class Node implements Comparable<Node> {

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 实现Comparable接口,若返回值大于0，则说明Node小，排前面，，即按从小到大顺序排
     * @param node 比较结点
     * @return int
     */
    @Override
    public int compareTo(Node node) {
        return this.value - node.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
