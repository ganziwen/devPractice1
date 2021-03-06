package io;

import java.io.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FileReaderAndWriter
 * @Description
 * @date 2021/7/10 10:00
 */
public class FileReaderAndWriter {
    public static void main(String[] args) {
        testReader();
    }

    /**
     * 字符流读取正确写法
     */
    public static void testReader() {
        String filePath = "Basic/src/main/java/io/testReadFile.txt";
        Reader reader = null;

        File file = new File(filePath);
        try {
            reader = new FileReader(file);
            int length = 0;
            char[] chars = new char[1024 * 256];
            while ((length = reader.read(chars)) != -1) {
                String string = new String(chars, 0, length);
                System.out.println(string);
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

    /**
     * 待补充
     */
    public static void testWriter() {

    }
}

