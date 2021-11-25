package com.eleven.icode.rabbitmq.base.pubsub;

import com.eleven.icode.rabbitmq.base.utils.RabbitConstant;
import com.eleven.icode.rabbitmq.base.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Scanner;

public class WeatherBureau {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = connection.createChannel();
        //第一个参数交换机名字   其他参数和之前的一样
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"" , null , input.getBytes());
        channel.close();
        connection.close();
    }
}
