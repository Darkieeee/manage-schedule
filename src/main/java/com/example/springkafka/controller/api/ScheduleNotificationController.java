package com.example.springkafka.controller.api;

import com.example.springkafka.entity.Schedule;
import com.example.springkafka.entity.ScheduleNotification;
import com.example.springkafka.entity.User;
import com.example.springkafka.service.ScheduleNotificationService;
import com.example.springkafka.service.ScheduleService;
import com.example.springkafka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ScheduleNotificationController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleNotificationService scheduleNotificationService;
    @Autowired
    UserService userService;

    @GetMapping("/register/notifications")
    public Map<String,Object> getRegisteredSchedules(@RequestParam(value = "username") String username,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "8") int size) {
        Map<String, Object> result = new HashMap<>();

        User user = userService.getUser(username);
        if(user == null) {
            result.put("success", false);
            result.put("message", "User not found");
        }
        else {
            result.put("success", true);
            result.put("data", scheduleNotificationService.getScheduleNotificationsByUser(user.getId(), page, size));
        }

        return result;
    }

    @PostMapping("/register/notifications")
    public Map<String, Object> registerScheduleNotification(@RequestParam(value = "scheduleId") int scheduleId,
                                                            @RequestParam(value = "username", defaultValue = "") String username) {
        Map<String, Object> result = new HashMap<>();

        Schedule schedule = scheduleService.getSchedule(scheduleId);
        User user = userService.getUser(username);

        if (schedule == null || user == null) {
            result.put("success", false);
            result.put("message", "Schedule or user not found");
        }
        else {
            ScheduleNotification scheduleNotification = new ScheduleNotification();
            scheduleNotification.setSchedule(schedule);
            scheduleNotification.setUser(user);
            scheduleNotification.setEnable(true);

            boolean created = scheduleNotificationService.upsert(scheduleNotification);

            if(created) {
                result.put("success", true);
                result.put("message", "Register schedule successfully");
            } else {
                result.put("success", false);
                result.put("message", "Register schedule unsuccessfully");
            }
        }

        return result;
    }

    @PutMapping("/register/notifications/{id}")
    public Map<String, Object> updateScheduleNotification(@PathVariable int id,
                                                          @RequestParam(value = "enable") boolean enable) {
        Map<String, Object> result = new HashMap<>();
        ScheduleNotification scheduleNotification = scheduleNotificationService.getScheduleNotification(id);

        if (scheduleNotification == null) {
            result.put("success", false);
            result.put("message", "Schedule notification not found");
        }
        else {
            scheduleNotification.setEnable(enable);

            boolean update = scheduleNotificationService.upsert(scheduleNotification);
            if(update) {
                result.put("success", true);
                result.put("message", "Update notification successfully");
            } else {
                result.put("success", false);
                result.put("message", "Update notification unsuccessfully");
            }
        }

        return result;
    }
}
