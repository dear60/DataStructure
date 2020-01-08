package com.mingful.www.datastructure.huffman.huffmanunzip;

import com.mingful.www.datastructure.huffman.huffmanzip.HuffmanZip;

/**
 * @author fmf
 * @version 1.0
 * @className HuffmanUnZipTester
 * @description 赫夫曼编码经行数据解压测试类
 * @create 2020-01-07 15:25
 **/
public class HuffmanUnZipTester {
    public static void main(String[] args) {
        String sourcePath = "./src/main/resources/file/test.sql";
        String destPath = "./src/main/resources/file/test.zip";
        // 工程的绝对路径
        System.out.println(System.getProperty("user.dir"));
        HuffmanZip.zipFile(sourcePath, destPath);
        String sourceRestorePath = "./src/main/resources/file/testRestore.sql";
        HuffmanUnZip.unZipFile(destPath, sourceRestorePath);
    }
}

