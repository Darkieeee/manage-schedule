package com.example.springkafka.controller.restful;

import com.example.springkafka.entity.Todo;
import com.example.springkafka.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoService service;

    @GetMapping("/api/todos")
    public List<Todo> getTodos() {
        return service.getTodos();
    }

    @DeleteMapping("/api/deleteTodo/{id}")
    public void deleteTodo(@PathVariable int id) {
    }

}
