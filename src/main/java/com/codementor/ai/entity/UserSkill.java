package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_skills",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "skill_id"}
))
@Data
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "skill_id",nullable = false)
    private Skill skill;

    @Column(nullable = false,length = 30)
    private String proficiency="BEGINNER";

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    @PreUpdate
    private  void preUpdate(){
        updatedAt=LocalDateTime.now();
    }
}
