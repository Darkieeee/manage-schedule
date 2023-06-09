package com.example.springkafka.notification.consumer;

import com.example.springkafka.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class NotificationConsumer {
    @Autowired
    NotificationService notificationService;

    @KafkaListener(id = "myId", topics = "schedule_notification")
    public void listen(String message) {
        notificationService.notify(message);
    }
}
