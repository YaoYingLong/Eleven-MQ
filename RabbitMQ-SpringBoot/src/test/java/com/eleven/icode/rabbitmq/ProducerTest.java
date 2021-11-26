package com.eleven.icode.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate; // 注入RabbitTemplate

    @Test
    public void send() {
        rabbitTemplate.convertAndSend("boot_topic_exchange", "boot.haha", "boot mq...");
    }
}
