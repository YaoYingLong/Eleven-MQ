package com.eleven.icode.rabbitmq.basic;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    // 定义方法进行信息的监听RabbitListener中的参数用于表示监听的是哪一个队列
    @RabbitListener(queues = "boot_queue")
    public void ListenerQueue(Message message) {
        System.out.println("message:" + new String(message.getBody()));
    }
}
