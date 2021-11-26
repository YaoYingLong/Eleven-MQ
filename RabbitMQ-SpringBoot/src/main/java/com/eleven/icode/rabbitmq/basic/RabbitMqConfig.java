package com.eleven.icode.rabbitmq.basic;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //定义交换机的名字
    public static final String  EXCHANGE_NAME = "boot_topic_exchange";
    //定义队列的名字
    public static final String QUEUE_NAME = "boot_queue";


    @Bean(value = "bootExchange")
    public Exchange bootExchange(){  // 1、声明交换机
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean(value = "bootQueue")
    public Queue bootQueue(){ // 2、声明队列
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    // 3、队列与交换机进行绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier(value = "bootQueue") Queue queue,
                                     @Qualifier(value = "bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
