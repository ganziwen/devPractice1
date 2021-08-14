package strategy;

import chain.FileType;
import chain.MyFile;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ParseStrategyManger
 * @Description
 * @date 2021/8/14 23:39
 */
public final class ParseStrategyManger {

    private final Map<FileType, IStrategy<MyFile>> strategyMap;

    private ParseStrategyManger() {
        this.strategyMap = Maps.newConcurrentMap();
        this.strategyMap.put(FileType.MP4, new Mp4ParseStrategy());
        this.strategyMap.put(FileType.PNG, new PNGParseStrategy());
    }


    private static class ClassHolder {
        private static final ParseStrategyManger holder = new ParseStrategyManger();
    }

    public static ParseStrategyManger of() {
        return ClassHolder.holder;
    }

    public void doParse(MyFile myFile) {
        if (this.strategyMap.containsKey(myFile.getFileType())) {
            this.strategyMap.get(myFile.getFileType()).update(myFile);

        } else {
            System.out.println(String.format("FileType %s is not exist", myFile.getFileType()));
        }


    }

}
