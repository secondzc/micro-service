package com.tongyuan.ikafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangcy on 2018/6/5
 */
public class SimpleConsumerListener {

    @KafkaListener(id = "foo", topics = "topic-test")
    public void listen(ConsumerRecord record) {
        System.out.println("listen");
        System.out.println(record.key());
        System.out.println(record.value());
    }
}