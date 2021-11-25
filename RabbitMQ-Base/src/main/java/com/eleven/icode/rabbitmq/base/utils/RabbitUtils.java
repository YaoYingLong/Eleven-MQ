package com.eleven.icode.rabbitmq.base.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author by YingLong on 2021/11/25
 */
public class RabbitUtils {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    static {
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("eleven");
        connectionFactory.setPassword("eleven");
        connectionFactory.setVirtualHost("eleven");
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
