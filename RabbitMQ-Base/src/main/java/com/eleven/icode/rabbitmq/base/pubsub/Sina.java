package com.eleven.icode.rabbitmq.base.pubsub;

import com.eleven.icode.rabbitmq.base.utils.RabbitConstant;
import com.eleven.icode.rabbitmq.base.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Sina {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection(); //获取TCP长连接
        final Channel channel = connection.createChannel(); //获取虚拟连接
        channel.queueDeclare(RabbitConstant.QUEUE_SINA, false, false, false, null); //声明队列信息
        // queueBind用于将队列与交换机绑定，参数1：队列名 参数2：交互机名  参数三：路由key（暂时用不到)
        channel.queueBind(RabbitConstant.QUEUE_SINA, RabbitConstant.EXCHANGE_WEATHER, "");
        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA , false , new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag() , false);
            }
        });
    }

}
