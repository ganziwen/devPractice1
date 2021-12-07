package com.example.jvmsandboxdemo.model;

import lombok.Data;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/7-14:12
 */
@Data
public class Remote {
    private String user = "apps";
    private String host = "127.0.0.1";
    private int port = 22;
    private String password = "apps";
    private String identity = "~/.ssh/id_rsa";
    private String passphrase = "";

}
