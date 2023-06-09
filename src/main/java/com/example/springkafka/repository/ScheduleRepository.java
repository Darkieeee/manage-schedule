package com.example.springkafka.repository;

import com.example.springkafka.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Page<Schedule> findAllByUserId(int userId, Pageable pageable);
    @Query("SELECT s FROM Schedule s WHERE s.user.id <> ?1 AND NOT EXISTS " +
           "(SELECT sn FROM ScheduleNotification sn WHERE sn.schedule.id = s.id AND sn.user.id = ?1)")
    Page<Schedule> findAvailableSchedules(int userId, Pageable pageable);
}
