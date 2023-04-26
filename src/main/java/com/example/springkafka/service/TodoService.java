package com.example.springkafka.service;

import com.example.springkafka.entity.Todo;
import com.example.springkafka.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodo(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    public List<Todo> getTodo(String title) {
        return todoRepository.findByTitle(title);
    }

    public Todo updateTodo(Todo todo) {
        Todo todoFound = getTodo(todo.getId());

        todoFound.setTitle(todo.getTitle());
        todoFound.setDateCreated(todo.getDateCreated());

        return todoRepository.save(todoFound);
    }

    public void deleteTodo(int id) {
        todoRepository.deleteById(id);
    }

}
