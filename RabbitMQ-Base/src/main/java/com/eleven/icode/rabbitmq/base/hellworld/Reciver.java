package com.eleven.icode.rabbitmq.base.hellworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author by YingLong on 2021/11/25
 */
public class Reciver extends DefaultConsumer {
    private Channel channel;
    public Reciver(Channel channel) {
        super(channel);
        this.channel = channel;
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body);
        System.out.println("消费者接收到的消息：" + message + "，消息的TagId：" + envelope.getDeliveryTag());
        // false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
