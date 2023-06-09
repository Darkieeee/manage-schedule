package com.example.springkafka.controller;

import com.example.springkafka.security.CustomUserDetails;
import com.example.springkafka.service.ScheduleNotificationService;
import com.example.springkafka.service.ScheduleService;
import com.example.springkafka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;

@Controller
public class HomeController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleNotificationService scheduleNotificationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/home", "index"}, method = RequestMethod.GET)
    public String index(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                        Model model) {
        int page = 0;
        int size = 8;

        if(customUserDetails != null) {
            int userId = customUserDetails.getId();
            System.out.println(scheduleService.getAvailableSchedules(userId, page, size).getContent().size());

            model.addAttribute("user", userService.getUser(userId));
            model.addAttribute("available_schedules", scheduleService.getAvailableSchedules(userId, page, size).getContent());
            model.addAttribute("registered_schedules", scheduleNotificationService.getScheduleNotificationsByUser(userId, page, size).getContent());
            model.addAttribute("my_schedules", scheduleService.getSchedules(userId, page, size).getContent());
        }
        else {
            model.addAttribute("available_schedules", scheduleService.getSchedules(page, size));
        }
        return "index";
    }
}
