package strategy;

import chain.FileType;
import chain.MyFile;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description 策略设计模式
 * @date 2021/8/14 23:35
 */
public class App {
    public static void main(String[] args) {
        MyFile myFile = MyFile.builder().fileType(FileType.AVI).fileName("testFileName").filePath("/tmp/").build();
        ParseStrategyManger.of().doParse(myFile);
    }
}
