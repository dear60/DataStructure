package com.mingful.www.datastructure.tree;

/**
 * @author fmf
 * @version 1.0
 * @className BinaryTreeTester
 * @description 二叉树
 * @create 2019-12-30 15:58
 **/
public class BinaryTreeTester {

    public static void main(String[] args) {

        Node node1 = new Node(1, "fmf");
        Node node2 = new Node(2, "zxh");
        Node node3 = new Node(3, "zgl");
        Node node4 = new Node(4, "ll");
        Node node5 = new Node(5, "wh");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node4.setRight(node5);
        BinaryTree binaryTree = new BinaryTree(node1);
        System.out.println("========== 先序遍历 ==========");
        binaryTree.preTraversal(binaryTree.getRoot());
        System.out.println("========== 中序遍历 ==========");
        binaryTree.infixTraversal(binaryTree.getRoot());
        System.out.println("========== 后序遍历 ==========");
        binaryTree.postTraversal(binaryTree.getRoot());
        System.out.println("========== 先序查找 ==========");
        System.out.println(binaryTree.find(9));
        System.out.println("========== 顺序二叉树 ==========");
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.arrayToTree(0);
        System.out.println("========== 线索二叉树 ==========");

        Node node6 = new Node(6, "wxj");
        Node node7 = new Node(7, "sym");
        Node node8 = new Node(8, "zzw");
        Node node9 = new Node(9, "hmh");
        node2.setLeft(node6);
        node2.setRight(node7);
        node6.setLeft(node8);
        node6.setRight(node9);
        System.out.println("结点4的前驱结点为" + node4.getLeft());
        System.out.println("结点4的后继结点为" + node4.getRight());

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(node1);
        threadedBinaryTree.preOrder();

        System.out.println("结点4的前驱结点为" + node4.getLeft());
        System.out.println("结点4的后继结点为" + node4.getRight());
        System.out.println("========== 线索二叉树遍历 ==========");
        threadedBinaryTree.traversal();
    }
}

/**
 * 线索化二叉树
 */
class ThreadedBinaryTree {

    private Node root;

    private Node pre = null;

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    public void preOrder() {
        preOrder(root);
    }
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        Node preNode = node.getLeft();
        Node nextNode = node.getRight();
        if (preNode == null) {
            // 让当前结点的左指针指前结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            // 让前结点的右指针指向当前结点
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        preOrder(preNode);
        preOrder(nextNode);
    }

    /**
     * 中序遍历
     */
    public void traversal() {
        Node node = root;
        while (node != null) {
            System.out.println(node);
            if (node.getLeft() != null && node.getLeftType() == 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }
}

/**
 * 顺序(数组二叉树)
 */
class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }
    public void arrayToTree(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if (index > array.length - 1 || index < 0) {
            System.out.println("数组下标越界");
            return;
        }
        // 给定一个数组，顺序存储树，采用先序遍历将树输出
        System.out.print(array[index] + " ");
        if ((index * 2 + 1) < array.length) {
            arrayToTree(index * 2 + 1);
        }
        if ((index * 2 + 2) < array.length) {
            arrayToTree(index * 2 + 2);
        }
    }
}

class BinaryTree {

    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }


    public void preTraversal(Node node) {

        System.out.println(node);
        if (node.getLeft() != null) {
            preTraversal(node.getLeft());
        }
        if (node.getRight() != null) {
            preTraversal(node.getRight());
        }
    }
    public void infixTraversal(Node node) {

        if (node.getLeft() != null) {
            infixTraversal(node.getLeft());
        }
        System.out.println(node);
        if (node.getRight() != null) {
            infixTraversal(node.getRight());
        }
    }
    public void postTraversal(Node node) {

        if (node.getLeft() != null) {
            postTraversal(node.getLeft());
        }
        if (node.getRight() != null) {
            postTraversal(node.getRight());
        }
        System.out.println(node);
    }

    public Node find(int id) {
       return find(root, id);
    }
    public Node find(Node node, int id) {
        if (node == null) {
            return null;
        }
        if (node.getId() == id) {
            return node;
        }
        Node temp = null;
        if (node.getLeft() != null) {
            temp = find(node.getLeft(), id);
        }
        if (temp == null && node.getRight() != null) {
            temp = find(node.getRight(), id);
        }
        return temp;
    }
}

class Node {

    private int id;
    private String name;
    private Node left;
    private Node right;

    /**
     * 如果为0,left表示为左子树，为1,left表示为前驱结点
     */
    private int leftType;
    /**
     * 如果为0,right表示为又子树，为1,left表示为后继结点
     */
    private int rightType;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
