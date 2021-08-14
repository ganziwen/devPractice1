package strategy;

import chain.MyFile;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Mp4ParseStrategy
 * @Description
 * @date 2021/8/14 23:35
 */
public class Mp4ParseStrategy implements IStrategy<MyFile> {
    @Override
    public void update(MyFile myFile) {
        System.out.println("Mp4ParseStrategy.parse");

    }
}
