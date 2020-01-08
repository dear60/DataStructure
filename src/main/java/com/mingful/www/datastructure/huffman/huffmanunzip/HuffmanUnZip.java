package com.mingful.www.datastructure.huffman.huffmanunzip;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fmf
 * @version 1.0
 * @className HuffmanUnZip
 * @description 赫夫曼编码经行数据解压
 * @create 2020-01-07 16:06
 **/
public class HuffmanUnZip {

    /**
     * 解压文件
     * @param sourcePath 源文件路径
     * @param destPath 目的文件路径
     */
    public static void unZipFile(String sourcePath, String destPath) {
        try (InputStream is = new FileInputStream(sourcePath);
             OutputStream os = new FileOutputStream(destPath);
             ObjectInputStream ois = new ObjectInputStream(is)){

            byte[] source = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodeTable = (Map<Byte, String>)ois.readObject();
            byte[] dest = unZip(source, huffmanCodeTable);
            os.write(dest);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节数组转字符串
     *
     * @param target 压缩后的字节数组
     * @return String 0/1字符串
     */
    public static String byteToString(byte[] target) {
        StringBuilder stringBuilder = new StringBuilder();

        String temp = "";
        int value;
        // 头字节保存的是最后一个字节的有效位
        int lastLength = target[0];
        for (int i = 1; i < target.length; i++) {
            value = target[i];
            // 将正数值扩充到九位，方便之后裁剪
            if (value >= 0) {
                value |= 256;
            }
            temp = Integer.toBinaryString(value);
            // 只留八位
            temp = temp.substring(temp.length() - 8);
            // 裁剪最后一个字节的有效位
            if (i == target.length - 1) {
                temp = temp.substring(8 - lastLength);
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }


    /**
     * 解压
     *
     * @param s                传入的0/1字符串
     * @param huffmanCodeTable 赫夫曼编码表
     * @return byte[] 源文件的字节码
     */
    public static byte[] unZip(String s, Map<Byte, String> huffmanCodeTable) {
        Map<String, Byte> newCodeTable = new HashMap<>(huffmanCodeTable.size());
        for (Map.Entry<Byte, String> entry : huffmanCodeTable.entrySet()) {
            newCodeTable.put(entry.getValue(), entry.getKey());
        }

        // 左指针
        int left = 0;
        // 滑动指针
        int cursor = 0;
        String temp;
        // 不确定最终有多少字节，先用list接收
        List<Byte> list = new ArrayList<>();
        // 从第一位开始遍历，若在赫夫曼编码表找到与之对应的字节，则left移动到cursor处，开始下一个字符的寻找，直到结束
        while (true) {
            cursor++;
            if (cursor > s.length()) {
                break;
            }
            temp = s.substring(left, cursor);
            if (newCodeTable.get(temp) != null) {
                list.add(newCodeTable.get(temp));
                left = cursor;
            }
        }
        byte[] bytes = new byte[list.size()];
        int i = 0;
        for (Byte b : list) {
            bytes[i] = b;
            i++;
        }
        return bytes;
    }

    /**
     * 封装方法
     *
     * @param target           压缩文件字节数组
     * @param huffmanCodeTable 赫夫曼编码表
     * @return 源文件字节数组
     */
    public static byte[] unZip(byte[] target, Map<Byte, String> huffmanCodeTable) {
        String s = byteToString(target);
        return unZip(s, huffmanCodeTable);
    }
}
