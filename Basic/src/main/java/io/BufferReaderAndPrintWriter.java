package io;

import java.io.*;

/**
 * 一行一行读取
 *
 * @author Ganziwen
 * @version 1.0
 * @ClassName BufferReaderAndPrintWriter
 * @Description
 * @date 2021/7/10 11:43
 */
public class BufferReaderAndPrintWriter {
    public static void main(String[] args) {
        testBufferReader();
    }

    public static void testBufferReader() {
        String filePath = "Basic/src/main/java/io/testReadFile.txt";
        BufferedReader reader = null;

        try {
            FileReader fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);
            while (reader.readLine() != null) {
                System.out.println(reader.readLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("文件不存在" + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("文件读取失败" + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("流关闭失败" + e.getMessage());
                }
            }
        }
    }
}
