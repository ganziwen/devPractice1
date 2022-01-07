package com.development.mock.chain;

import com.development.mock.model.MappingParamData;
import com.development.mock.model.MappingParamInfo;
import com.development.mock.model.MockContext;
import com.development.mock.util.JsonFactory;
import com.development.mock.util.YmlUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.tinylog.Logger;

import java.io.File;

import static com.development.mock.constants.PathConstant.MOCK_DATA_ROOT_PATH_VIP;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/7-11:50
 * 单个的匹配
 */
public class SingleMappingHandler extends AbstractMockHandler<MockContext, String> {
    @Override
    protected boolean preHandle(MockContext mockContext) {
        Pair<String, File> fileStringPair = judgeFile(mockContext);
        if (fileStringPair.getRight().isFile()) {
            Logger.info("{} is file", fileStringPair.getRight());
            return true;

        } else {
            return false;
        }
    }

    @Override
    protected String onHandle(MockContext mockContext) {
        Pair<String, File> fileStringPair = judgeFile(mockContext);
        try {
            MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(YmlUtils.readForObject(fileStringPair.getLeft(), MappingParamData.class));
            Logger.info("读取的mock文件信息为 {}", JsonFactory.objectToJson(mappingParamInfo));
            Logger.info("返回内容为{}", JsonFactory.objectToJson(mappingParamInfo));
            // 单文件就不用去匹配，直接拿文件返回就完事了,但是有个问题是单文件怎么知道要返回 yml 的文件呢，或者强行将路径最后拼接一个 yml 直接读取呢
            return mappingParamInfo.getResponse();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
