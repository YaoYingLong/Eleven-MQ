package com.eleven.icode.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author by YingLong on 2021/11/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer-basic.xml")
public class ProducerBasicTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHelloWorld() {
        rabbitTemplate.convertAndSend("spring_queue", "hello world spring...");
    }

    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "spring fanout...");
    }

    @Test
    public void testDirect() {
        rabbitTemplate.convertAndSend("spring_direct_exchange", "info", "spring_direct...");
    }

    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "eleven.hehe.haha", "spring topic...");
    }
}
