package chain;


import chain.nochain.AVIParse;
import chain.nochain.MP4Parse;
import chain.nochain.PNGParse;
import chain.usechain.FileParseManager;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description 责任链设计模式
 * @date 2021/8/14 11:39
 */
public class App {
    public static void main(String[] args) {
        testNoChain();
        testUseChain();
    }

    /**
     * 不用责任链的效果
     */
    public static void testNoChain() {
        MyFile myFile = MyFile.builder().fileType(FileType.AVI).fileName("testFileName").filePath("/tmp/").build();

        // 根据文件的不同类型找不同的解析器进行解析
        if (myFile.getFileType() == FileType.AVI) {
            new AVIParse().parse();
        } else if (myFile.getFileType() == FileType.MP4) {
            new MP4Parse().parse();
        } else if (myFile.getFileType() == FileType.PNG) {
            new PNGParse().parse();
        }

    }

    /**
     * 利用责任链以及单例
     */
    public static void testUseChain() {
        MyFile myFile = MyFile.builder().fileType(FileType.AVI).fileName("testFileName").filePath("/tmp/").build();
        FileParseManager.of().doParse(myFile);
    }
}
