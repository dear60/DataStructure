package com.mingful.www.datastructure.huffman;

/**
 * @author fmf
 * @version 1.0
 * @className HuffmanNode
 * @description 赫夫曼树结点
 * @create 2020-01-06 15:04
 **/
public class HuffmanNode implements Comparable<HuffmanNode> {

    /**
     * 字符的ascii值
     */
    private Byte value;
    /**
     * 字符出现的个数
     */
    private Integer count;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(Byte value, Integer count) {
        this.value = value;
        this.count = count;
    }

    public Byte getValue() {
        return value;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.count - o.count;
    }
}
