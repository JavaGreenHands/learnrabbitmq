package com.learnning.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息接受示例
 */
public class RabbitmqReciveDemo {
    //声明队列名称
    private final static String QUEUE_DEMO_NAME = "demoqueue";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //声明队列是为了防止在生产者启动之前启动消费者
        channel.queueDeclare(QUEUE_DEMO_NAME,false,false,false,null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //官方提供的回调接口
        // we provide a callback in the form of an object that will buffer the messages until we're ready to use them.
        DeliverCallback deliverCallback = (consumerTag,delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_DEMO_NAME, true, deliverCallback, consumerTag -> { });


    }
}
