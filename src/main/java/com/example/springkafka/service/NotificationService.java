package com.example.springkafka.service;

import com.example.springkafka.entity.Schedule;
import com.example.springkafka.entity.ScheduleNotification;
import com.example.springkafka.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NotificationService {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    ScheduleNotificationService scheduleNotificationService;
    @Autowired
    ScheduleService scheduleService;

    public void notify(String message) {
        int scheduleId = Integer.parseInt(message);
        List<ScheduleNotification> scheduleNotifications = scheduleNotificationService.getScheduleNotificationsBySchedule(scheduleId);
        Schedule schedule = scheduleService.getSchedule(scheduleId);
        String notification = String.format("Một công việc mới vửa được thêm vào lịch trình " + schedule.getTitle());

        for(ScheduleNotification scheduleNotification: scheduleNotifications) {
            if(scheduleNotification.isEnable()) {
                sendMessageToUser(scheduleNotification.getUser().getUsername(), notification);
            }
        }
    }

    private void sendMessageToUser(String username, String message) {
        messagingTemplate.convertAndSendToUser(username, "/topic/notifications", message);
    }
}
