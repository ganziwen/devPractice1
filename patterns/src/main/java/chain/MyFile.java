package chain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.File;
import java.nio.file.attribute.FileTime;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MyFile
 * @Description
 * @date 2021/8/14 11:39
 */
@Data
@Builder
public class MyFile {
    private FileType fileType;
    private String fileName;
    private String filePath;

}
