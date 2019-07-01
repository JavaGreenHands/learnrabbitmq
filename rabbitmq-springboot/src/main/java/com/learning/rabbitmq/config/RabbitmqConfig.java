package com.learning.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ配置类
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 声明一个交换机
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MqQueue.EXCHANGE_DIRECT_ONE);
    }


    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue queue1(){
        return new Queue(MqQueue.QUEUE_DIRECT_TYPE_ONE);
    }

    @Bean
    public Queue queue2(){
        return new Queue(MqQueue.QUEUE_DIRECT_TYPE_TWO);
    }

    @Bean
    public Queue queue3(){
        return new Queue(MqQueue.QUEUE_DIRECT_TYPE_Three);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    Binding binding1(Queue queue1,DirectExchange directExchange){

        return BindingBuilder.bind(queue1).to(directExchange).with(MqQueue.QUEUE_DIRECT_TYPE_ONE);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    Binding binding2(Queue queue2,DirectExchange directExchange){

        return BindingBuilder.bind(queue2).to(directExchange).with(MqQueue.QUEUE_DIRECT_TYPE_TWO);
    }


    /**
     * 绑定队列和交换机
     */
    @Bean
    Binding binding3(Queue queue3,DirectExchange directExchange){

        return BindingBuilder.bind(queue3).to(directExchange).with(MqQueue.QUEUE_DIRECT_TYPE_Three);
    }
}
