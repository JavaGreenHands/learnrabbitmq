package com.learning.rabbitmq.controller;

import com.learning.rabbitmq.send.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    private MessageSend messageSend;

    @RequestMapping("/test/a")
    public String test1(String a ){
        messageSend.send1(a);
        return "success";
    }
    @RequestMapping("/test/b")
    public String test2(String a ){
        messageSend.send2(a);
        return "success";
    }
    @RequestMapping("/test/c")
    public String test3(String a ){
        messageSend.send3(a);
        return "success";
    }

}
