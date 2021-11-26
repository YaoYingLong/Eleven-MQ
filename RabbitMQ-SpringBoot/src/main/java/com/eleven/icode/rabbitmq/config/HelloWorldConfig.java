package com.eleven.icode.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连模式只需要声明队列，所有消息都通过队列转发，无需设置交换机
 */
@Configuration
public class HelloWorldConfig {
	@Bean
	public Queue setQueue() {
		return new Queue("helloWorldqueue");
	}
}
