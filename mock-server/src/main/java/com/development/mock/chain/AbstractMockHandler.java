package com.development.mock.chain;

import com.development.mock.model.MockContext;
import org.apache.commons.lang3.tuple.Pair;
import org.tinylog.Logger;

import java.io.File;
import java.util.Objects;

import static com.development.mock.constants.PathConstant.MOCK_DATA_ROOT_PATH;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/7-11:04
 */
public abstract class AbstractMockHandler<T, R> {
    private AbstractMockHandler<T, R> nextHandler;

    protected abstract boolean preHandle(T context);

    protected abstract R onHandle(T context);

    public void setNextHandler(AbstractMockHandler<T, R> nextHandler) {
        this.nextHandler = nextHandler;
    }


    public R doHandle(T context) {
        if (Objects.isNull(context)) {
            throw new IllegalArgumentException("context should not be null");
        }
        // 先判断能否处理
        if (preHandle(context)) {
            // 能处理，则处理
            return onHandle(context);
        }
        // 不能处理，则找下一个解析器，并且进行处理
        if (!Objects.isNull(this.nextHandler)) {
            return this.nextHandler.doHandle(context);
        }
        // 找不到可处理的解析器就抛异常
        throw new IllegalStateException("cant not find handler,is not file or dictionary");
    }

    public Pair<String, File> judgeFile(MockContext mockContext) {
        String filePath = MOCK_DATA_ROOT_PATH + mockContext.getMockFileName();
        File mockDataFile = new File(filePath);
        Pair<String, File> filePair = Pair.of(filePath, mockDataFile);
        if (!mockDataFile.exists()) {
            Logger.info("文件/路径:{}不存在", filePath);
            throw new IllegalStateException(filePath + "不存在");
        } else {
            return filePair;
        }
    }


}
