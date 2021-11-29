package com.eleven.icode.rocketmq.config;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

@ExtRocketMQTemplateConfiguration(value = "extRocketMQTemplate")
public class ExtRocketMQTemplate extends RocketMQTemplate {

}
