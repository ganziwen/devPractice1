package com.example.autoframework.cases.envprofile;

import com.example.autoframework.annotation.*;
import com.example.autoframework.dac.factory.DataSourceFactory;

import javax.sql.DataSource;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName testEnvProfile
 * @Description
 * @date 2021/12/23 10:55
 */
@EnvProfile("dev")// 临时加，调试用
public class testEnvProfile {
    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("测试环境变量选取")
    @CaseTag(key = "name", val = "val")
    @CaseDesc(desc = "老测试", owner = "qa")
    @EnvProfile("dev")// 临时加，调试用
    public void testNormal() {
        /*
        1. envprofile 用来标志我们希望运行时的 profile 信息
        2. 基于 junit 5 来做扩展，在运行用例前，将此 profile 的数据，塞进一个全局信息内
        3. 后续需要来用 profile 切换时，从此全局信息中捞出来具体的 profile 是啥
        4. 为保配置读取失败，可以设置一个兜底的，如 config-default.yml
        5. 思考:如果每个用例都写一个 profile，难道我们的 profile 每次都得改？
         */

        DataSource dataSource = DataSourceFactory.of().getDataSource();
    }
}
