package com.learnning.rabbitmq.workqueue.persistence;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息持久化
 */
public class MessagePersistenceSend {

    public static final String QUEUE_NAME = "persitencequeue";


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        //默认的ip为localhost
        connectionFactory.setHost("localhost");
        //默认的port为5672
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个持久化队列
        boolean durable =true;
        channel.queueDeclare(QUEUE_NAME,durable,false,false,null);

        //将消息标记为持久化并不能完全的保证消息不会丢失，虽然它会将消息保存到磁盘
        //但是当RabbitMQ接受消息并且尚未保存消息时，仍然有一个短时间窗口。 此外，RabbitMQ不会为每条消息执行fsync（2）
        // - 它可能只是保存到缓存而不是真正写入磁盘。 持久性保证不强，但对于我们简单的任务队列来说已经足够了。
        // 如果您需要更强的保证，那么您可以使用发布者确认
        String message = " hello persitence! ";
        channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());



    }
}
