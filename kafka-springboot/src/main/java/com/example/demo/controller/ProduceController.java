package com.example.demo.controller;

import com.example.demo.component.CommonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ProduceController
 * @Description
 * @date 2021/10/31 16:20
 */
@RestController
public class ProduceController {
    @Autowired
    private CommonProducer producer;

    @RequestMapping("/send")
    public String sendMsg(@RequestParam("msg") String msg) {
        producer.sendMsg(msg);
        return "success";
    }
}
