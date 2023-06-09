package com.example.springkafka.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Date;

@Table
@Entity
@Data
@EnableJpaAuditing
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(columnDefinition="bit default 0", nullable = false)
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="schedule")
    @JsonBackReference
    private Schedule schedule;
}
