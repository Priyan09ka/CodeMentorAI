package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "assessment_questions",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"assessment_id", "position"}
))
@Data
public class AssessmentQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "assessment_id",nullable = false)
    private Assessment assessment;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "problem_id",nullable = false)
    private Problem problem;

    @Column(name = "question_test",nullable = false,columnDefinition = "TEXT")
    private String questionTest;

    @Column(nullable = false)
    private Integer points=1;

    @Column(nullable = false)
    private Integer position;
}
