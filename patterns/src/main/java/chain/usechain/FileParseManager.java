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

    // 构建一个调用链:mp4->avi->png
    private AbstractHandlder<MyFile> initParseHandleChain() {
        AbstractHandlder<MyFile> mp4ParseHandler = new MP4ParseHandler();
        AbstractHandlder<MyFile> aviParseHandler = new AVIParseHandler();
        AbstractHandlder<MyFile> pngParseHandler = new PNGParseHandler();

        //设置 avi 为 MP4 的 下一个节点
        mp4ParseHandler.addNextHandler(aviParseHandler);
        // png 作为 avi 的下一个节点
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
