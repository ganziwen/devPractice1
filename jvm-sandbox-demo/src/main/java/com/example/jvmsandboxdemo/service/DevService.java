package com.example.jvmsandboxdemo.service;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.log.StaticLog;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;


/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/7-14:09
 * 就是连接两台服务器进行 zip 的复制以及解压，主要的逻辑并不在这里实现
 */
@Service
@Slf4j
public class DevService {

    /**
     * host 是 ip 的地址
     * 这里就是将打好的 jar 包直接从源服务器复制到目的服务器
     * 或者先直接用 resource 内的 sandbox 文件
     *
     * @param host
     */
    public void openLog(String host) {

        try {

            Session srcSession = JschUtil.getSession("10.199.188.29", 22, "root", "vipshop123");
            Session targetSession = JschUtil.getSession(host, 22, "apps", "apps");
            srcSession.connect(6000);
            if (srcSession.isConnected()) {
                StaticLog.info("Host " + srcSession.getHost() + " connected.");
            }
            String filePath = "/apps/svr/sandbox.zip";
            String tempFilePath = new File(System.getProperty("user.dir")).getAbsolutePath() + "/sandbox.zip";

            srcSession.disconnect();

            targetSession.connect(6000);

            if (targetSession.isConnected()) {
                StaticLog.info("Host " + targetSession.getHost() + " connected.");
            }

            JschUtil.exec(targetSession, "unzip -o /apps/svr/sandbox.zip -d /apps/svr/", Charset.defaultCharset());
            Thread.sleep(200);
            JschUtil.exec(targetSession, "chmod 777 /apps/svr/sandbox/bin/start.sh", Charset.defaultCharset());
            JschUtil.exec(targetSession, "/apps/svr/sandbox/bin/start.sh", Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}