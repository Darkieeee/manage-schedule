package com.example.springkafka.controller.api;

import com.example.springkafka.entity.Schedule;
import com.example.springkafka.entity.Todo;
import com.example.springkafka.notification.producer.NotificationProducer;
import com.example.springkafka.service.ScheduleService;
import com.example.springkafka.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    NotificationProducer producer;

    @Value("${spring.kafka.topic-name}")
    private String topicName;

    @PostMapping("/schedules/{scheduleId}/add-new-todo")
    public Map<String, Object> addTodo(@PathVariable int scheduleId,
                                       @RequestParam("title") String todoTitle) {
        Map<String, Object> result = new HashMap<>();

        try {
            Schedule schedule = scheduleService.getSchedule(scheduleId);

            if(schedule != null) {
                //Create a Todo instance
                Todo todo = new Todo();

                //Set variables to this Todo
                todo.setTitle(todoTitle);
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                todo.setDateCreated(date);
                todo.setSchedule(schedule);
                Todo savedTodo = todoService.addTodo(todo);

                //While a new Todo is saved, Kafka producer will send a message to consumer to notify the client
                producer.send(Integer.toString(scheduleId));

                //A success result will be returned when save successfully
                result.put("success", true);
                result.put("todo", savedTodo);
            }
            else {
                //A fail result will be returned when schedule not found
                result.put("success", false);
                result.put("message", "Schedule not found");
            }
        }
        catch(Exception e) {
            //A fail result will be returned when an exception error (not determined) be found
            result.put("success", true);
            result.put("message", e.getMessage());
        }

        return result;
    }

    @GetMapping("/schedules/{scheduleId}/todos")
    public List<Todo> getTodos(@PathVariable int scheduleId) {
        return todoService.getTodos(scheduleId);
    }

}
