package com.learnning.rabbitmq.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列的消费者
 * 消息确认机制
 * 如何确认我们的消息永远不会丢失，rabbitmq支持消息确认机制
 * 如果一个消费者挂掉，可能是channel 关闭了，链接关闭了，或者是tcp无法链接，没有发送ack rabbitmq
 * 就会确认这个消息并没有处理成功，并且会尝试重新将他加入到队列中
 * 如果这时有其他的消费者，rabbitmq将会快速的将它交付给其他的消费者工作，可以确定消息永不丢失
 * 如果一些消息超时，rabbitmq将会重新发送消息在消费者挂掉时，即使处理消息需要非常长的时间，也没关系。
 * 默认使用手动确认消息的机制
 *
 * 消息的持久化：
 * 消息的确认机制只是在程序中防止消息未被消费，但是人生总会有很多意外呢
 * 已经被定义为非持久化的队列，rabbitmq不允许我们使用不同的参数定义同一个队列
 *
 */
public class WorkQueuesWorkerDemo {

    /**
     * 声明队列名称
     */
    private final static String QUEUE_TASK_NAME = "workqueuedemo";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connection = new ConnectionFactory();

        connection.setHost("localhost");

        Connection conn = connection.newConnection();
        Channel channel = conn.createChannel();

        /*
         * 1 队列名称
         * 2 如果我们声明一个持久队列，则为true（队列将在服务器重启后继续存在）
         * 3 声明一个独占队列则为true,此队列仅用于本次连接使用
         * 4 是否开启自动删除功能，当服务器不再使用时自动删除队列
         * 5 队列的其他属性
         *
         */
        channel.queueDeclare(QUEUE_TASK_NAME,false,false,false,null);


        DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            try{
                doWork(message);
            }finally {
                System.out.println("[x] Done");
            }
        };
        //自动发送确认机制
        boolean autoAck = true;

        channel.basicConsume(QUEUE_TASK_NAME, autoAck, deliverCallback, consumerTag -> { });

    }

    /**
     * 处理消息
     * @param task
     */
    private static void doWork(String task) {

        System.out.println(task);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
