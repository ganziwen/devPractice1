package chain.usechain;

import chain.FileType;
import chain.MyFile;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MP4ParseHandler
 * @Description
 * @date 2021/8/14 16:30
 */
public class MP4ParseHandler extends AbstractHandlder<MyFile> {
    @Override
    protected boolean preHandle(MyFile myFile) {
        return myFile.getFileType() == FileType.MP4;

    }

    @Override
    protected void onHandle(MyFile myFile) {
        System.out.println("MP4ParseHandler.onHandle");
    }
}
