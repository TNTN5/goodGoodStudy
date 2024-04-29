package com.zz.springbootKafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private KafkaTemplate<String,String> kafka;

    @RequestMapping("/sendData")
    public String data(String msg){
        kafka.send("",msg);
        return "ok";
    }
}
