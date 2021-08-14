package chain.usechain;

import chain.MyFile;

import java.util.AbstractList;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FileParseManager
 * @Description
 * @date 2021/8/14 16:36
 */
public final class FileParseManager {
    private AbstractHandlder<MyFile> fileParseHandlder;

    private FileParseManager() {
        this.fileParseHandlder = initParseHandleChain();
    }

    private AbstractHandlder<MyFile> initParseHandleChain() {
        AbstractHandlder<MyFile> mp4ParseHandler = new MP4ParseHandler();
        AbstractHandlder<MyFile> aviParseHandler = new AVIParseHandler();
        AbstractHandlder<MyFile> pngParseHandler = new PNGParseHandler();

        mp4ParseHandler.addNextHandler(aviParseHandler);
        aviParseHandler.addNextHandler(pngParseHandler);
        return mp4ParseHandler;
    }

    private static class ClassHolder {
        private static final FileParseManager holder = new FileParseManager();
    }

    public static FileParseManager of() {
        return ClassHolder.holder;
    }

    public void doParse(MyFile myFile) {
        this.fileParseHandlder.doHandle(myFile);
    }
}
