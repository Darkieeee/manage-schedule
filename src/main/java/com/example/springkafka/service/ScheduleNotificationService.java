package com.example.springkafka.service;

import com.example.springkafka.entity.ScheduleNotification;
import com.example.springkafka.repository.ScheduleNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleNotificationService {
    @Autowired
    private ScheduleNotificationRepository repository;

    public ScheduleNotification getScheduleNotification(int id) {
        return repository.findById(id).orElse(null);
    }
    public List<ScheduleNotification> getScheduleNotifications() {
        return repository.findAll();
    }

    public Page<ScheduleNotification> getScheduleNotificationsByUser(int userId, int page, int size) {
        return repository.findAllByUserId(userId, PageRequest.of(page, size));
    }

    public List<ScheduleNotification> getScheduleNotificationsByUser(int userId) {
        return repository.findAllByUserId(userId);
    }

    public Page<ScheduleNotification> getScheduleNotificationsBySchedule(int scheduleId, int page, int size) {
        return repository.findAllByScheduleId(scheduleId, PageRequest.of(page, size));
    }

    public List<ScheduleNotification> getScheduleNotificationsBySchedule(int scheduleId) {
        return repository.findAllByScheduleId(scheduleId);
    }

    public boolean upsert(ScheduleNotification scheduleNotification) {
        try {
            repository.save(scheduleNotification);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(int scheduleId, int userId) {
        try {
            repository.deleteByScheduleIdAndUserId(scheduleId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
