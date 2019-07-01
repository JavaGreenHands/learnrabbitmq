package com.learnning.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * 工作队列
 */
public class WorkQueuesTaskDemo {
    /**
     * 声明队列名称
     */
    private final static String QUEUE_TASK_NAME = "workqueuedemo";

    /**
     * 消息服务器特性之一：削峰
     *工作队列，又名任务队列，为了防止立即执行特别密集的任务，也就是常见的高并发，可以把任务放到队列中，而后慢慢执行
     * 在后端中慢慢处理请求，不至于很多请求一次打到服务器上将服务器搞挂
     * 这种情况在微服务中尤为常见
     * @param args
     */
    public static void main(String[] args) {

        //创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        try {

            //通过链接工厂创建一个链接
            Connection connection = connectionFactory.newConnection();
            //创建一个channel
            Channel channel = connection.createChannel();
            //声明一个队列，如果该队列不存在将会自动创建
            channel.queueDeclare(QUEUE_TASK_NAME,false,false,false,null);

            Scanner scanner = new Scanner(System.in,"UTF-8");
            String message = scanner.next();

            while (true){

                if ("exit".equals(message)){
                    System.exit(0);

                }
                channel.basicPublish("",QUEUE_TASK_NAME,null,message.getBytes());
                System.out.println("send message ... "+ message);

            }


        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
