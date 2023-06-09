package com.example.springkafka.notification.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationTopicConfig {
    @Bean
    public NewTopic topic1() {
        return new NewTopic("schedule_notification", 1, (short) 1);
    }
}
