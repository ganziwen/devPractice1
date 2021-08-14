package strategy;

import chain.MyFile;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PNGParseStrategy
 * @Description
 * @date 2021/8/14 23:38
 */
public class PNGParseStrategy implements IStrategy<MyFile> {
    @Override
    public void update(MyFile myFile) {
        System.out.println("PNGParseStrategy.parse");
    }
}
