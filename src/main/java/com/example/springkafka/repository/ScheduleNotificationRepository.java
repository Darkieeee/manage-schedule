package com.example.springkafka.repository;

import com.example.springkafka.entity.ScheduleNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleNotificationRepository extends JpaRepository<ScheduleNotification, Integer> {
    Page<ScheduleNotification> findAllByUserId(int userId, Pageable pageable);
    Page<ScheduleNotification> findAllByScheduleId(int scheduleId, Pageable pageable);
    List<ScheduleNotification> findAllByScheduleId(int scheduleId);
    List<ScheduleNotification> findAllByUserId(int userId);
    void deleteByScheduleIdAndUserId(int scheduleId, int userId);
}
