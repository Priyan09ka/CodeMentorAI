package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test_cases")
@Data
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "problem_id",nullable = false)
    private Problem problem;

    @Column(name = "input_data",nullable = false,columnDefinition = "TEXT")
    private String inputData;

    @Column(name = "expected_output",nullable = false,columnDefinition = "TEXT")
    private String expectedOutput;

    @Column(name = "is_sample",nullable = false)
    private boolean sample=false;
}
