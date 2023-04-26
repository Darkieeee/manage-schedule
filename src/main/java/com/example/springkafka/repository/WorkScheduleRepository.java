package com.example.springkafka.repository;

import com.example.springkafka.entity.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {
}
