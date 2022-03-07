package io.file_stream;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/1-20:38
 */
public class TestFileStream {

    /**
     * 读字节流
     *
     * @throws IOException
     */
    @Test
    public void testFileInputStream() throws IOException {
        File file = new File("./src/main/resources/test.txt");
        if (file.isFile()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            // 缓冲区
            byte[] cache = new byte[1024 * 512];
            // 每次捞出来的长度
            int n;
            // 当 n = -1 时,代表到了文件的末尾
            while ((n = fileInputStream.read(cache)) != -1) {
                String string = new String(cache, 0, n);
                System.out.println(string);
            }
            // 关闭 io 流
            fileInputStream.close();
        }

    }

    /**
     * 写字节流
     */
    @Test
    public void testFileOutputStream() throws IOException {
        File file = new File("./src/main/resources");
        if (!file.exists()) {
            file.mkdirs();
        }
        // true 代表 append 参数的值， 追加写
        FileOutputStream fileOutputStream = new FileOutputStream("./src/main/resources/input.txt", true);
        fileOutputStream.write("this is input\n".getBytes(StandardCharsets.UTF_8));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * try with resource
     */
    @Test
    public void testTryWithResource() {

        try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/input.txt")) {
            // 缓冲区
            byte[] cache = new byte[1024 * 512];
            // 每次捞出来的长度
            int n;
            // 当 n = -1 时,代表到了文件的末尾
            while ((n = fileInputStream.read(cache)) != -1) {
                String string = new String(cache, 0, n);
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
