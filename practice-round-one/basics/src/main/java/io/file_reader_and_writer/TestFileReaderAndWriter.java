package io.file_reader_and_writer;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/1-22:03
 */
public class TestFileReaderAndWriter {
    /**
     * 字符流设置缓冲区读取
     */
    @Test
    public void testFileReader() {
        try (FileReader fileReader = new FileReader("./src/main/resources/test.txt")) {
            char[] chars = new char[128];
            int n;
            while ((n = fileReader.read(chars)) != -1) {
                String string = new String(chars, 0, n);
                System.out.println(string);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流不用缓冲区,独取出来是一个字一个字的读
     */
    @Test
    public void testFileReaderWithOutCache() {
        try (FileReader fileReader = new FileReader("./src/main/resources/test.txt")) {
            int n;
            while ((n = fileReader.read()) != -1) {
                System.out.println((char) n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写字符流
     */
    @Test
    public void testFileWriter() {
        File file;
        try (FileWriter fileWriter = new FileWriter(new File("./src/main/resources/input.txt"), true)) {
            fileWriter.write("hello test\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * bufferReader ,一行一行读
     */
    @Test
    public void testBufferReader() {
        File file;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./src/main/resources/test.txt")))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入
     */
    @Test
    public void testPrintWriter() {
        try (PrintWriter printWriter = new PrintWriter("./src/main/resources/input.txt")) {
            printWriter.println("换行插入");
            printWriter.print("不换行插入");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTrans() {
        try {

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("./src/main/resources/input.txt"))));
            printWriter.println("这是插入的信息");
            printWriter.flush();
            printWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream("./src/main/resources/input.txt"))));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
