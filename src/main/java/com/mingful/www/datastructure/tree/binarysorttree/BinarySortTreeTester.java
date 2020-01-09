package com.mingful.www.datastructure.tree.binarysorttree;

/**
 * @author fmf
 * @version 1.0
 * @className BinarySortTreeTester
 * @description 二叉排序树(BST)
 * @create 2020-01-09 9:25
 **/
public class BinarySortTreeTester {

    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] array = {7, 3, 10, 12, 5, 1, 9, 8, 11};
        for (int value : array) {
            binarySortTree.add(new Node(value));
        }
        // 中序遍历二叉树
        binarySortTree.infixOrder();

        System.out.println("--------------------");
        // 删除叶子结点
        int i = 1;
        binarySortTree.remove(i);
        binarySortTree.infixOrder();
        System.out.println("--------------------");
        // 删除有一个子树的结点
        i = 12;
        binarySortTree.remove(i);
        binarySortTree.infixOrder();
        System.out.println("--------------------");
        // 删除有有两个子树的结点
        i = 7;
        binarySortTree.remove(i);
        binarySortTree.infixOrder();
        System.out.println("--------------------");
        // 删除多个
        binarySortTree.remove(3);
        binarySortTree.remove(10);
        binarySortTree.remove(5);
        binarySortTree.remove(9);
        binarySortTree.infixOrder();
        System.out.println("--------------------");
        // 删除所有
        binarySortTree.remove(8);
        binarySortTree.remove(11);
        binarySortTree.infixOrder();


    }
}

class BinarySortTree {
    private Node root;

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


    /**
     * 删除结点
     * 存在三种情况：
     * 1. 该节点是叶子结点，即没有左右子树
     * 2. 该节点有一颗子树
     * 3. 该节点有两颗子树
     *
     * @param value 要删除的值
     */
    public void remove(int value) {
        // 核心思想就是找到该结点的父节点，然后判断是左子树还是右子树，置为null
        Node current = root.find(value);
        Node parent = root.findParent(value);
        if (current == null) {
            System.out.println("不存在该值");
            return;
        }
        // 没有子树
        if (current.getLeft() == null && current.getRight() == null) {
            // 没有父节点，说明是根结点
            if (parent == null) {
                root = null;
                return;
            }
            // 判断是父节点的左还是右
            if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else if (parent.getRight() == current) {
                parent.setRight(null);
            }

            // 有两颗子树
        } else if (current.getLeft() != null && current.getRight() != null) {
            // 从当前结点的左子树找个最大的代替当前结点，或者从右子树找个最小的代替当前结点
            Node replaceNode = current.getRight().minNode();
            // 删除最小值的结点(叶子结点)
            remove(replaceNode.getValue());
            current.setValue(replaceNode.getValue());

            // 有一颗子树
        } else {
            // 子节点
            Node childrenNode = null;
            // 判断子树是右子树还是左子树
            if (current.getLeft() != null) {
                childrenNode = current.getLeft();
            }
            if (current.getRight() != null) {
                childrenNode = current.getRight();
            }
            // 没有父节点，说明是根结点
            if (parent == null) {
                root = childrenNode;
            } else {
                if (parent.getLeft() == current) {
                    parent.setLeft(childrenNode);
                }
                if (parent.getRight() == current) {
                    parent.setRight(childrenNode);
                }
            }
        }
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
     * 查询值为value的结点
     *
     * @param value 值
     * @return Node
     */
    public Node find(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left != null) {
                return this.left.find(value);
            }
        } else {
            if (this.right != null) {
                return this.right.find(value);
            }
        }
        return null;
    }

    /**
     * 查询值为value的结点的父节点
     *
     * @param value 值
     * @return Node
     */
    public Node findParent(int value) {
        boolean isParent = (this.left != null && this.left.value == value) || (this.right != null && this.right.value == value);
        if (isParent) {
            return this;
        }
        if (value < this.value && this.left != null) {
            return this.left.findParent(value);
        }
        if (value > this.value && this.right != null) {
            return this.right.findParent(value);
        }
        return null;
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
    }

    /**
     * 找当前结点下的最小值
     *
     * @return Node
     */
    public Node minNode() {
        Node node = this;
        while (true) {
            if (node.left == null) {
                break;
            }
            node = node.left;
        }
        return node;
    }

    /**
     * 找当前结点下的最大值
     *
     * @return Node
     */
    public Node maxNode() {
        Node node = this;
        while (true) {
            if (node.right == null) {
                break;
            }
            node = this.left;
        }
        return node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void setValue(int value) {
        this.value = value;
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
