package com.learning.rabbitmq.send;

import com.learning.rabbitmq.bean.User;
import com.learning.rabbitmq.config.MqQueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送
 */
@Component
public class MessageSend {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送消息
     * @param message
     */
    public void send1(String message){
        this.rabbitTemplate.convertAndSend(MqQueue.EXCHANGE_DIRECT_ONE, MqQueue.QUEUE_DIRECT_TYPE_ONE, message);


    }    /**
     * 发送消息
     * @param message
     */
    public void send2(String message){
        this.rabbitTemplate.convertAndSend(MqQueue.EXCHANGE_DIRECT_ONE, MqQueue.QUEUE_DIRECT_TYPE_TWO, message);


    }    /**
     * 发送消息
     * @param message
     */
    public void send3(String message){
        this.rabbitTemplate.convertAndSend(MqQueue.EXCHANGE_DIRECT_ONE, MqQueue.QUEUE_DIRECT_TYPE_Three, message);


    }




}
