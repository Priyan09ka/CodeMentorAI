package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="problems")
@Data
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private Difficulty difficulty;

    @Column(name = "created_at", nullable = false,updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    public  enum Difficulty{
        EASY,
        MEDIUM,
        HARD
    }
}
