package com.example.autoframework.engine.listener;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.log.StaticLog;
import com.example.autoframework.alarm.callback.AlarmCallback;
import com.example.autoframework.model.FailureResult;
import com.example.autoframework.util.ReflectUtils;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.Optional;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FailureListener
 * @Description 通过监听器来监听获取执行结果
 * @date 2021/11/27 11:14
 */
public class FailureListener implements TestExecutionListener {

    private final String token;
    private Class<? extends AlarmCallback> alarmCallback;

    public FailureListener(String token, Class<? extends AlarmCallback> alarmCallback) {
        this.token = token;
        this.alarmCallback = alarmCallback;
    }

    /**
     * 实现的是 executionFinished 这个方法,其实还可以实现例如 skip 等方法
     */
    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        // StaticLog.info(testExecutionResult.toString());
        TestExecutionResult.Status status = testExecutionResult.getStatus();
        // 基于 status 进行用例执行失败状态的判断,当 status==Status.FAILED时,表示这个用例执行失败,此刻我们在批量运行用例时就拿到了单个用例执行失败的一个回调
        // 既然在这里做了失败的回调,那此时就可以将必要的信息处理之后,做发出（报警）
        if (TestExecutionResult.Status.FAILED != status) {
            return;
        }

        // 1. 将回调回来的信息做一个封装
        Optional<TestSource> optional = testIdentifier.getSource();
        if (!optional.isPresent()) {
            return;
        }
        TestSource testSource = optional.get();
        // 只有 methodSource 才能进来,比如什么 ClassSource 是进不来的
        if (!(testSource instanceof MethodSource)) {
            return;
        }
        // 将 testSource 强转成 MethodSource ,得到失败测试cases的类名、方法名,参数信息
        MethodSource methodSource = (MethodSource) testSource;
        String className = methodSource.getClassName();
        String methodName = methodSource.getMethodName();
        String methodParameterTypes = methodSource.getMethodParameterTypes();

        // 得到失败测试cases的报错信息
        Optional<Throwable> throwableOptional = testExecutionResult.getThrowable();
        if (!throwableOptional.isPresent()) {
            return;
        }
        Throwable throwable = throwableOptional.get();

        FailureResult failureResult = FailureResult.builder().className(className).methodName(methodName).parameterTypes(methodParameterTypes).throwable(throwable).token(this.token).build();
        // 处理报警的逻辑

        // 实现了 callback，在这里进行处理告警，这里利用的是自己写的反射工具类处理
        ReflectUtils.newInstance(alarmCallback).postExecutionFailure(failureResult);
        // 这里是利用了 hutool 的反射工具类直接使用
        // ReflectUtil.newInstance(AlarmCallback.class).postExecutionFailure(failureResult);

    }
}
