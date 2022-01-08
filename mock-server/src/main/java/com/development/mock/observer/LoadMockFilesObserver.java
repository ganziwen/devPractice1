package com.development.mock.observer;

import cn.hutool.core.io.FileUtil;
import com.development.mock.model.MockDataEntity;
import com.development.mock.model.MockDataInfo;
import com.development.mock.model.MockContext;
import com.development.mock.util.PathUtils;
import com.development.mock.util.YmlUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.development.mock.constants.PathConstant.MOCK_DATA_ROOT_PATH;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LoadMockFilesObserver
 * @Description
 * @date 2022/1/8 16:07
 */
public class LoadMockFilesObserver implements IObserver<MockContext> {
    @Override
    public void update(MockContext mockContext) {
        List<MockDataInfo> mockDataInfoList = FileUtil.listFileNames(MOCK_DATA_ROOT_PATH + mockContext.getMockFileName()).parallelStream().map(fileName -> {
            String path = PathUtils.joinPath(MOCK_DATA_ROOT_PATH + mockContext.getMockFileName(), fileName);
            // 将 url 和 fileName 拼接，取出文件中的 mock 数据
            return MockDataInfo.fromMappingParamData(YmlUtils.readForObject(path, MockDataEntity.class));
        }).collect(Collectors.toList());
        // 将文件 load 进 mockContext 里面
        mockContext.setMockDataInfoList(mockDataInfoList);
    }
}
