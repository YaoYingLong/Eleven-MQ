package com.eleven.icode.rocketmq.basic;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 注意下@RocketMQMessageListener这个注解的其他属性
 */
@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroupV2", messageModel = MessageModel.CLUSTERING,
        topic = "TestTopic", consumeMode = ConsumeMode.CONCURRENTLY, selectorExpression = "TagB")
public class SpringConsumerV2 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("Received message2 : " + message);
    }
}
