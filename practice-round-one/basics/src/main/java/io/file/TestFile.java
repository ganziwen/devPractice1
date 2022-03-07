package io.file;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/1-16:51
 */
public class TestFile {

    /**
     * file 的基本操作
     *
     * @throws IOException
     */
    @Test
    public void testFile() throws IOException {
        File file = new File("./");
        // 获取到本 project 的路径
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

        System.out.println("file.getName() = " + file.getName());
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());

        // 判断file是否存在
        System.out.println("file.exists() = " + file.exists());
        // 是否是文件
        System.out.println("file.isFile() = " + file.isFile());
        // 是否是路径
        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.length() = " + file.length());

        // 获取 file 下的 文件,以及一级目录
        String[] fileList1 = file.list();
        Arrays.asList(fileList1).forEach(System.out::println);

        // 获取 file 下的 文件,以及一级目录,可以根据其得到绝对路径
        File[] listFiles = file.listFiles();
        Arrays.asList(listFiles).forEach(var1 -> System.out.println(var1.getAbsolutePath()));

        System.out.println("++");
        // 筛选,内部类里面的 accept 返回的是满足的条件
        Arrays.stream(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        })).forEach(var1 -> System.out.println(var1.getAbsolutePath()));

    }

    /**
     * 返回 .java 文件的绝对路径
     */
    @Test
    public void testFindJavaFile() {
        File file = new File("./");
        getJavaFile(file);
    }

    public void getJavaFile(File file) {
        File[] javaFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getAbsolutePath().endsWith(".java");
            }
        });
        Arrays.asList(javaFiles).forEach(var1 -> System.out.println(var1.getAbsolutePath()));
        File[] packages = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        for (File aPackage : packages) {
            getJavaFile(aPackage);
        }

    }

}
