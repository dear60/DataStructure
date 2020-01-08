package com.mingful.www.datastructure.huffman.huffmanzip;

import com.mingful.www.datastructure.huffman.HuffmanNode;

import java.io.*;
import java.util.*;

/**
 * @author fmf
 * @version 1.0
 * @className HuffmanZip
 * @description 赫夫曼编码经行数据压缩
 * @create 2020-01-07 16:09
 **/
public class HuffmanZip {

    /**
     * 赫夫曼编码表
     */
    private static Map<Byte, String> codeTable = new HashMap<>();

    private static HuffmanNode root = null;

    /**
     * 压缩文件
     * @param sourcePath 源文件路径
     * @param destPath 目标文件路径
     */
    public static void zipFile(String sourcePath, String destPath) {
        // IO流自动关闭语法糖(需实现AutoCloseable)
        try (InputStream is = new FileInputStream(sourcePath);
             OutputStream os = new FileOutputStream(destPath);
             ObjectOutputStream oos = new ObjectOutputStream(os)){

            byte[] source = new byte[is.available()];
            is.read(source);
            byte[] dest = zip(source);
            Map<Byte, String> huffmanCodeTable = getHuffmanCodeTable();
            oos.writeObject(dest);
            oos.writeObject(huffmanCodeTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从字节文本中得到结点
     *
     * @param content 内容
     * @return List<HuffmanNode>
     */
    public static List<HuffmanNode> getNodes(byte[] content) {

        List<HuffmanNode> huffmanNodeList = new ArrayList<>();
        boolean flag;
        for (byte temp : content) {
            // 遍历集合，若有该结点，则count + 1，若无，则创建新的结点
            flag = true;
            for (HuffmanNode huffmanNode : huffmanNodeList) {
                if (huffmanNode.getValue() == temp) {
                    huffmanNode.setCount(huffmanNode.getCount() + 1);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                huffmanNodeList.add(new HuffmanNode(temp, 1));
            }

        }
        return huffmanNodeList;
    }

    /**
     * 将结点集合初始化成赫夫曼树
     *
     * @param huffmanNodeList 结点集合
     * @return HuffmanNode 头结点
     */
    public static HuffmanNode initHuffmanTree(List<HuffmanNode> huffmanNodeList) {
        HuffmanNode leftNode;
        HuffmanNode rightNode;
        HuffmanNode parent;
        while (huffmanNodeList.size() > 1) {
            // 排序
            Collections.sort(huffmanNodeList);
            leftNode = huffmanNodeList.get(0);
            rightNode = huffmanNodeList.get(1);

            // 非叶子结点的value为null
            parent = new HuffmanNode(null, leftNode.getCount() + rightNode.getCount());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            huffmanNodeList.remove(leftNode);
            huffmanNodeList.remove(rightNode);
            huffmanNodeList.add(parent);
        }

        root = huffmanNodeList.get(0);
        // 返回根结点
        return huffmanNodeList.get(0);
    }

    /**
     * 先序遍历
     *
     * @param root 根结点
     */
    public static void preOrderTraversal(HuffmanNode root) {

        System.out.println(root);
        if (root.getLeft() != null) {
            preOrderTraversal(root.getLeft());
        }
        if (root.getRight() != null) {
            preOrderTraversal(root.getRight());
        }
    }

    /**
     * 封装initCodeTable方法，方便调用
     *
     * @param root 根结点
     * @return Map<Byte, String> 字符与对应的0/1表示的键值对
     */
    public static Map<Byte, String> getHuffmanCodeTable(HuffmanNode root) {
        initCodeTable(root, "", new StringBuilder());
        return codeTable;
    }

    public static Map<Byte, String> getHuffmanCodeTable() {
        initCodeTable(root, "", new StringBuilder());
        return codeTable;
    }

    /**
     * 初始化赫夫曼编码表
     *
     * @param huffmanNode   根结点
     * @param code          约定左子树为0，右子树为1
     * @param stringBuilder 可变字符串
     */
    public static void initCodeTable(HuffmanNode huffmanNode, String code, StringBuilder stringBuilder) {
        // 此处将code提出来而不直接在递归经行拼接(如temp.append("0"))，是因为会污染右递归的数值
        StringBuilder temp = new StringBuilder(stringBuilder).append(code);
        if (huffmanNode != null) {
            // 若结点的value值为空，说明是非叶子结点，也就不是出现的字符
            if (huffmanNode.getValue() == null) {
                // 左递归
                initCodeTable(huffmanNode.getLeft(), "0", temp);
                // 右递归
                initCodeTable(huffmanNode.getRight(), "1", temp);
            } else {
                codeTable.put(huffmanNode.getValue(), temp.toString());
            }
        }
    }

    /**
     * 压缩
     *
     * @param source           源目标内容的字节数组
     * @param huffmanCodeTable 赫夫曼编码表
     * @return byte[]          压缩后的字节数组
     */
    public static byte[] zip(byte[] source, Map<Byte, String> huffmanCodeTable) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : source) {
            stringBuilder.append(huffmanCodeTable.get(b));
        }
        // System.out.println(stringBuilder.toString());
        // 得到压缩后的字节数组大小
        int length = (stringBuilder.length() + 7) / 8;
        // 第一个字节保存最后一个字节的有效位
        byte[] ret = new byte[length + 1];
        String sub;
        int index = 1;
        // 最后一个字节的有效位
        int lastLength;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            // 若剩下的不足八位，防止下标越界
            if (i + 8 > stringBuilder.length()) {
                sub = stringBuilder.substring(i);
                lastLength = sub.length();
                ret[0] = (byte) lastLength;
            } else {
                sub = stringBuilder.substring(i, i + 8);
            }
            // 先转成二进制的整型，然后转成字节型(注意，转后的值是补码)
            ret[index] = (byte) Integer.parseInt(sub, 2);
            index++;
        }
        return ret;
    }

    /**
     * 封装所有方法，方便调用
     *
     * @param source 源目标内容的字节数组
     * @return byte[] 压缩后的字节数组
     */
    public static byte[] zip(byte[] source) {

        List<HuffmanNode> huffmanNodeList = HuffmanZip.getNodes(source);
        HuffmanNode root = HuffmanZip.initHuffmanTree(huffmanNodeList);
        Map<Byte, String> codeTable = HuffmanZip.getHuffmanCodeTable(root);
        return HuffmanZip.zip(source, codeTable);
    }
}
