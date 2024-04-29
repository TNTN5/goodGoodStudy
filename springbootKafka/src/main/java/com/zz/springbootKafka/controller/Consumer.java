package com.zz.springbootKafka.controller;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class Consumer {
    @KafkaListener(topics = "tipicName")
    public void consumerTopic(String msg){
        System.out.println(msg);
    }
}
