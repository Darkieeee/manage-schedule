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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    UserService userService;
    @Autowired
    ScheduleNotificationService scheduleNotificationService;

    @GetMapping("/schedules")
    public Map<String, Object> getSchedules(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "8") int size,
                                            @RequestParam("username") String username) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUser(username);

        if (user == null) {
            result.put("success", false);
            result.put("message", "User not found");
        } else {
            result.put("success", true);
            result.put("data", scheduleService.getSchedules(user.getId(), page, size));
        }

        return result;
    }

    @PostMapping("/schedules/add-new-schedule")
    public Map<String, Object> addSchedule(@RequestParam("username") String username,
                                           @RequestParam("title") String scheduleTitle,
                                           @RequestParam(value = "description", required = false) String scheduleDescription) {
        Map<String, Object> data = new HashMap<>();

        if(scheduleTitle.isBlank() || username.isBlank()) {
            data.put("success", false);
            data.put("message", "Required keys mustn't have their null or blank value");
        }
        else {
            User user = userService.getUser(username);
            if(user == null) {
                data.put("success", false);
                data.put("message", "User not found");
            }

            Schedule schedule = new Schedule();
            schedule.setTitle(scheduleTitle);
            schedule.setDescription(scheduleDescription);
            schedule.setUser(user);
            Schedule savedSchedule = scheduleService.addSchedule(schedule);

            if (savedSchedule != null) {
                data.put("success", true);
                data.put("schedule", schedule);
            } else {
                data.put("success", false);
                data.put("message", "Couldn't save new schedule to database");
            }
        }

        return data;
    }
}
