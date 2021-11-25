package com.eleven.icode.rabbitmq.base.confirm;


import com.eleven.icode.rabbitmq.base.utils.RabbitConstant;
import com.eleven.icode.rabbitmq.base.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Map<String, String> area = new LinkedHashMap<String, String>();
        area.put("china.hunan.changsha.20201127", "中国湖南长沙20201127天气数据");
        area.put("china.hubei.wuhan.20201127", "中国湖北武汉20201127天气数据");
        area.put("china.hunan.zhuzhou.20201127", "中国湖南株洲20201127天气数据");
        area.put("us.cal.lsj.20201127", "美国加州洛杉矶20201127天气数据");
        area.put("china.hebei.shijiazhuang.20201128", "中国河北石家庄20201128天气数据");
        area.put("china.hubei.wuhan.20201128", "中国湖北武汉20201128天气数据");
        area.put("china.henan.zhengzhou.20201128", "中国河南郑州20201128天气数据");
        area.put("us.cal.lsj.20201128", "美国加州洛杉矶20201128天气数据");
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        //开启confirm监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //第二个参数代表接收的数据是否为批量接收，一般用不到。
                System.out.println("消息已被Broker接收,Tag:" + l);
            }
            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息已被Broker拒收,Tag:" + l);
            }
        });
        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return r) {
                System.err.println("===========================");
                System.err.println("Return编码：" + r.getReplyCode() + "-Return描述:" + r.getReplyText());
                System.err.println("交换机:" + r.getExchange() + "-路由key:" + r.getRoutingKey());
                System.err.println("Return主题：" + new String(r.getBody()));
                System.err.println("===========================");
            }
        });
        for (Map.Entry<String, String> entry : area.entrySet()) {
            // 第三个参数为：mandatory true代表如果消息无法正常投递则return回生产者，如果false，则直接将消息放弃。
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_TOPIC, entry.getKey(), true, null, entry.getValue().getBytes());
        }
        //如果关闭则无法进行监听，因此此处不需要关闭
//        channel.close();
//        connection.close();
    }
}
