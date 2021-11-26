package com.eleven.icode.rabbitmq.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author by YingLong on 2021/11/26
 */
public class ConsumerBasicTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq-consumer-basic.xml");
    }

}
