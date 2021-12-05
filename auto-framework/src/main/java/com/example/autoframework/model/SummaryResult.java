package com.example.autoframework.model;

import lombok.Data;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.List;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FailureInfo
 * @Description
 * @date 2021/12/5 15:35
 */
@Data
public class SummaryResult {
    private long timeStarted;

    private long timeFinished;

    private long totalFailureCount;

    private long containersFoundCount;

    private long containersStartedCount;

    private long containersSkippedCount;

    private long containersAbortedCount;

    private long containersSucceededCount;

    private long containersFailedCount;

    private long testsFoundCount;

    private long testsStartedCount;

    private long testsSkippedCount;

    private long testsAbortedCount;

    private long testsSucceededCount;

    private long testsFailedCount;

    private String token;

    private List<FailureInfo> failureInfos;

    private List<TestExecutionSummary.Failure> failures;

}
