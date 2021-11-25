package com.eleven.icode.rabbitmq.base.topic;

import com.eleven.icode.rabbitmq.base.utils.RabbitConstant;
import com.eleven.icode.rabbitmq.base.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Sina {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection(); // 获取TCP长连接
        final Channel channel = connection.createChannel(); //获取虚拟连接
        channel.queueDeclare(RabbitConstant.QUEUE_SINA, false, false, false, null); //声明队列信息
        // 指定队列与交换机以及routing key之间的关系
        channel.queueBind(RabbitConstant.QUEUE_SINA, RabbitConstant.EXCHANGE_WEATHER_TOPIC, "us.#");
        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
