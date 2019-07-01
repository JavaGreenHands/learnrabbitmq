package com.learning.rabbitmq.recive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息接受示例
 */
@Component
public class MessageReciver {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "direct.type1")
    public void test1(Message message, Channel channel) {

        System.out.println("recive from " + " direct.type1 ..." + "get message " + new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = "direct.type2")
    public void test2(Message message, Channel channel) {

        System.out.println("recive from " + " direct.type2 ..." + "get message " + new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = "direct.type3")
    public void test3(Message message, Channel channel) {

        System.out.println("recive from " + " direct.type3 ..." + "get message " + new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
