package com.learnning.rabbitmq.workqueue.persistence;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息持久化消费类
 */
public class MessagePersistenceRecv {


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(MessagePersistenceSend.QUEUE_NAME,true,false,false,null);

        DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
            try{
                System.out.println(" Received '" + message + "'");
            }finally {
                System.out.println(" [x] Done");
                //消息如果不确认，会一直阻塞
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };


        channel.basicConsume(MessagePersistenceSend.QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
}
