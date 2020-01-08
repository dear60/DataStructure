package com.mingful.www.datastructure.huffman.huffmanzip;

import com.mingful.www.datastructure.huffman.HuffmanNode;
import com.mingful.www.datastructure.huffman.huffmanunzip.HuffmanUnZip;

import java.util.*;

/**
 * @author fmf
 * @version 1.0
 * @className HuffmanZipTester
 * @description 赫夫曼编码经行数据压缩测试类
 * @create 2020-01-06 17:13
 **/
public class HuffmanZipTester {

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        byte[] contentByte = content.getBytes();
        /*
            System.out.println(Arrays.toString(contentByte));

            List<HuffmanNode> huffmanNodeList = HuffmanZip.getNodes(contentByte);
            System.out.println(huffmanNodeList);

            HuffmanNode root = HuffmanZip.initHuffmanTree(huffmanNodeList);
            System.out.println(root);

            Map<Byte, String> codeTable = HuffmanZip.getHuffmanCodeTable(root);
            System.out.println(codeTable);
        */

        byte[] zipByte = HuffmanZip.zip(contentByte);
        // System.out.println(Arrays.toString(zipByte));

        String s = HuffmanUnZip.byteToString(zipByte);
        System.out.println(s);
        Map<Byte, String> codeTable = HuffmanZip.getHuffmanCodeTable();
        byte[] source = HuffmanUnZip.unZip(s, codeTable);
        System.out.println(new String(source));
    }
}

