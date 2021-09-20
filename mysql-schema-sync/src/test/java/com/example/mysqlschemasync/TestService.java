package com.example.mysqlschemasync;

import com.example.mysqlschemasync.model.ConnectInfo;
import com.example.mysqlschemasync.model.SyncTableRequest;
import com.example.mysqlschemasync.service.SyncService;
import com.example.mysqlschemasync.service.impl.SyncServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/10-14:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
    @Autowired
    SyncService syncService;

    ConnectInfo srcConnectInfo;
    ConnectInfo dstConnectInfo;

    @Before
    public void init() {
        srcConnectInfo = new ConnectInfo();
        srcConnectInfo.setUrl("jdbc:mysql://www.xiaowenshu.cn:3399");
        srcConnectInfo.setUserName("root");
        srcConnectInfo.setPassWord("123456");

        dstConnectInfo = new ConnectInfo();
        dstConnectInfo.setUrl("jdbc:mysql://www.xiaowenshu.cn:3388");
        dstConnectInfo.setUserName("root");
        dstConnectInfo.setPassWord("123456");
    }

    @Test
    public void testSyncServiceImpl() {
        SyncTableRequest syncTableRequest = new SyncTableRequest();
        syncTableRequest.setSrcConnectInfo(srcConnectInfo);
        syncTableRequest.setDbName("test_table");
        syncTableRequest.setTableName("tb_user");
        syncTableRequest.setDstConnectInfo(dstConnectInfo);
        syncService.doSyncTable(syncTableRequest);
    }

}
