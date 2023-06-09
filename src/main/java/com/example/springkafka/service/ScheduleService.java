package com.example.springkafka.service;

import com.example.springkafka.entity.ScheduleNotification;
import com.example.springkafka.entity.Schedule;
import com.example.springkafka.repository.ScheduleNotificationRepository;
import com.example.springkafka.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleNotificationRepository scheduleNotificationRepository;

    public Schedule getSchedule(int scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Page<Schedule> getSchedules(int page, int size) {
        return scheduleRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Schedule> getSchedules(int userId, int page, int size) {
        return scheduleRepository.findAllByUserId(userId, PageRequest.of(page, size));
    }

    public Page<Schedule> getAvailableSchedules(int userId, int page, int size) {
        return scheduleRepository.findAvailableSchedules(userId, PageRequest.of(page, size));
    }

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
