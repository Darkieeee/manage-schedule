package com.example.springkafka.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "workschedule")
@Data
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

}
