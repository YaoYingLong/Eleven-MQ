package com.eleven.icode.rabbitmq.base.hellworld;

import com.eleven.icode.rabbitmq.base.utils.RabbitConstant;
import com.eleven.icode.rabbitmq.base.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author by YingLong on 2021/11/25
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection(); // 获取TCP长连接
        Channel channel = connection.createChannel();
        // 声明并创建一个队列，如果队列已存在，则使用这个队列
        // 第一个参数：队列名称ID
        // 第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
        // 第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
        // 第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
        // 其他额外的参数, null
        channel.queueDeclare(RabbitConstant.QUEUE_HELLOWORLD, false, false, false, null);
        String message = "test message";
        channel.basicPublish("", RabbitConstant.QUEUE_HELLOWORLD, null, message.getBytes());
        channel.close();
        System.out.println("===发送成功===");
    }
}
