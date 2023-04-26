package com.example.springkafka.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @CreatedDate
    private Date dateCreated;
}
