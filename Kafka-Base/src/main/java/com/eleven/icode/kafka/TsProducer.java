package com.eleven.icode.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author by YingLong on 2021/12/5
 */
public class TsProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("transactional.id", "my-transactional-id");
        Producer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());
        producer.initTransactions(); //初始化事务
        try {

            producer.beginTransaction(); //开启事务
            for (int i = 0; i < 100; i++) {//发到不同的主题的不同分区
                producer.send(new ProducerRecord<>("hdfs-topic", Integer.toString(i), Integer.toString(i)));
                producer.send(new ProducerRecord<>("es-topic", Integer.toString(i), Integer.toString(i)));
                producer.send(new ProducerRecord<>("redis-topic", Integer.toString(i), Integer.toString(i)));
            }

            producer.commitTransaction(); //提交事务
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            producer.close();
        } catch (KafkaException e) {

            producer.abortTransaction(); //回滚事务
        }
        producer.close();
    }

}
