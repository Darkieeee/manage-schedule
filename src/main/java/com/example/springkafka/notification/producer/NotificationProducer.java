package com.example.springkafka.notification.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${spring.kafka.topic-name}")
    private String topicName;


    public void send(String message) {
        template.send(topicName, message);
    }
}
