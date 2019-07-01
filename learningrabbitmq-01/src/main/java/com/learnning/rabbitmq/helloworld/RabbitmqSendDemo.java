package com.learnning.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitmq发送消息类
 */
public class RabbitmqSendDemo {

    //声明队列名称
    private final static String QUEUE_DEMO_NAME = "demoqueue";

    public static void main(String[] args) {

        //创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //设置rabbitmq server 的地址，默认端口为5672使用默认端口可以不用写
        connectionFactory.setHost("localhost");
        try {

            //通过链接工厂创建一个链接
            Connection connection = connectionFactory.newConnection();
            //创建一个channel
            Channel channel = connection.createChannel();
            //声明一个队列，如果该队列不存在将会自动创建
            channel.queueDeclare(QUEUE_DEMO_NAME,false,false,false,null);

            String message = "Hello RabbitMQ!";

            channel.basicPublish("",QUEUE_DEMO_NAME,null,message.getBytes());
            System.out.println("send message ... "+ message);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

