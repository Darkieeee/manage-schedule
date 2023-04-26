package com.example.springkafka.repository;

import com.example.springkafka.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByTitle(String title);
}
