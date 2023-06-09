package com.example.springkafka.controller;

import com.example.springkafka.entity.Schedule;
import com.example.springkafka.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ManageScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedule/{scheduleId}")
    public String index(@PathVariable int scheduleId, Model model) {
        Schedule schedule = scheduleService.getSchedule(scheduleId);
        model.addAttribute("schedule", schedule);
        return "schedule";
    }
}
