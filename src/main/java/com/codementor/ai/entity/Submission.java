package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions",
indexes = {
        @Index(name = "idx_submissions_user_date",
        columnList = "user_id,submitted_at"),
        @Index(name = "idx_submissions_problem",
        columnList = "problem_id")
})
@Data
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "problem_id",nullable = false)
    private Problem problem;

    @Column(name = "source_code",nullable = false,columnDefinition = "TEXT")
    private String sourceCode;

    @Column(nullable = false,length = 50)
    private String language;

    @Column(nullable = false,length = 30)
    private String status="PENDING";

    @Column(name = "runtime_ms")
    private Integer runtimeMs;

    @Column(name = "memory_kb")
    private Integer memoryKb;

    @Column(name = "submitted_at",nullable = false,updatable = false)
    private LocalDateTime submittedAt=LocalDateTime.now();
}
