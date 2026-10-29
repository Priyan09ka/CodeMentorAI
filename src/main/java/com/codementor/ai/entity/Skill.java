package com.codementor.ai.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="skills")
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Skill() {
    }

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
