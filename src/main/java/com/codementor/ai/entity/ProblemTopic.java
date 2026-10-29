package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "problem_topics",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"problem_id", "topic_id"}
))
@Data
public class ProblemTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "problem_id",nullable = false)
    private Problem problem;

    @Column(name = "topic_name",nullable = false,length = 100)
    private String topicName;

}
