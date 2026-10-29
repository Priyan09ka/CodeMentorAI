package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_progress",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id","skill_id"}
))
@Data
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "skill_id",nullable = false)
    private Skill skill;

    @Column(name = "completed_count",nullable = false)
    private Integer completedCount=0;

    @Column(name = "total_count",nullable = false)
    private Integer totalCount=0;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        updatedAt = LocalDateTime.now();
    }
}
