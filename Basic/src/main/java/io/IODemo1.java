package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/9-15:34
 */
public class IODemo1 {

    public static void main(String[] args) throws Exception {
        testReadFile();
//        testWriteFile();
    }

    /**
     * 读文件
     *
     * @throws Exception
     */
    public static void testReadFile() throws Exception {
//        path 可以用针对工程的相对路径
        String filePath = "Basic/src/main/java/io/testReadFile.txt";
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        int length = 0;
//        申请一个 buffer ,256 字节
        byte[] buffer = new byte[256];
//        循环读取,当读取到 length = -1 的时候标志已经读到了末尾,则跳出循环
        while ((length = inputStream.read(buffer)) != -1) {
//            System.out.println(length);
//            打印 buffer 的内容
            String string = new String(buffer, 0, length);
            System.out.println(string);
        }
    }

    /**
     * 写文件
     *
     * @throws Exception
     */
    public static void testWriteFile() throws Exception {
        String filePath = "Basic/src/main/java/io/testWriteFile.txt";
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        for (int i = 0; i < 100; i++) {
            String string = "hello java " + i + "\n";
            outputStream.write(string.getBytes(StandardCharsets.UTF_8));
        }
        outputStream.flush();
    }

    /**
     * 正规写法(java7之前的写法)
     *
     * @throws Exception
     */
    public static void normalReadFile() {
        String filePath = "Basic/src/main/java/io/testReadFile.txt";
        InputStream inputStream = null;
        try {
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            int length = 0;
            byte[] buffer = new byte[256];
            while ((length = inputStream.read(buffer)) != -1) {
                String string = new String(buffer, 0, length);
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在" + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("文件读取失败" + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("流关闭失败" + e.getMessage());
                }
            }
        }
    }

    public static void normalWriteFile() throws Exception {

    }
}
