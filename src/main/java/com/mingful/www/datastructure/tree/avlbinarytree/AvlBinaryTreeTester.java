package com.mingful.www.datastructure.tree.avlbinarytree;

/**
 * @author fmf
 * @version 1.0
 * @className AvlBinaryTreeTester
 * @description 平衡二叉树
 * @create 2020-01-10 9:24
 **/
public class AvlBinaryTreeTester {

    public static void main(String[] args) {
        AvlBinaryTree avlBinaryTree = new AvlBinaryTree();

        //int[] array = {4, 3, 6, 5, 7, 8}; // 左旋
        int[] array = {10, 11, 7, 6 ,8, 9}; // 双旋
        for (int value : array) {
            avlBinaryTree.add(new Node(value));
        }
        // 中序遍历自平衡二叉树
        avlBinaryTree.infixOrder();
        System.out.println("树的高度为：" + avlBinaryTree.getRoot().getHeight());
        System.out.println("树的左子树高度为：" + avlBinaryTree.getRoot().getLeftHeight());
        System.out.println("树的右子树高度为：" + avlBinaryTree.getRoot().getRightHeight());
        System.out.println("根节点为：" + avlBinaryTree.getRoot());
    }
}

class AvlBinaryTree {

    private Node root = null;

    /**
     * 添加结点
     *
     * @param node 结点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }


    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空");
            return;
        }
        root.infixOrder();
    }

    public Node getRoot() {
        return root;
    }
}

class Node {


    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加结点
     *
     * @param node 结点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        // 自平衡操作首先要判断根结点的左右子树高度，如果相差的绝对值大于一，说明要进行平衡操作
        if (getLeftHeight() - getRightHeight() > 1) {
            // 如果当前结点的左子树的右子树高度大于当前结点的左子树的左子树高度，说明要先进行左旋
            if (this.left != null && this.left.getRightHeight() > this.left.getLeftHeight()) {
                this.left.leftRotate();
            }
            rightRotate();
            return;
        }
        if (getRightHeight() - getLeftHeight() > 1) {
            // 如果当前结点的右子树的左子树高度大于当前结点的右子树的右子树高度，说明要先进行右旋
            if (this.right != null && this.right.getLeftHeight() > this.right.getRightHeight()) {
                this.right.rightRotate();
            }
            leftRotate();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 获得左子树高度
     *
     * @return int
     */
    public int getLeftHeight() {
        if (this.left != null) {
            return this.left.getHeight();
        }
        return 0;
    }

    /**
     * 获得右子树高度
     *
     * @return int
     */
    public int getRightHeight() {
        if (this.right != null) {
            return this.right.getHeight();
        }
        return 0;
    }

    /**
     * 获取高度
     *
     * @return int
     */
    public int getHeight() {
        return Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    /**
     * 结点左旋
     */
    public void leftRotate() {
        // 创建新的结点，值为当前结点的值
        Node newNode = new Node(value);
        // 把新的结点左子树设置成当前结点的左子树
        newNode.left = this.left;
        // 把新的结点右子树设置成当前结点的右子树的左子树
        newNode.right = this.right.left;
        // 把当前结点的值替换成右子结点的值
        this.value = this.right.value;
        // 把当前结点的右子树设置成右子树的右子树
        this.right = this.right.right;
        // 把当前结点的左子树设置成新的结点
        this.left = newNode;
    }

    /**
     * 结点右旋
     */
    public void rightRotate() {
        // 创建新的结点，值为当前结点的值
        Node newNode = new Node(value);
        // 把新的结点左子树设置成当前结点的左子树的右子树
        newNode.left = this.left.right;
        // 把新的结点右子树设置成当前结点的右子树
        newNode.right = this.right;
        // 把当前结点的值替换成左子结点的值
        this.value = this.left.value;
        // 把当前结点的左子树设置成左子树的左子树
        this.left = this.left.left;
        // 把当前结点的右子树设置成新的结点
        this.right = newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
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
