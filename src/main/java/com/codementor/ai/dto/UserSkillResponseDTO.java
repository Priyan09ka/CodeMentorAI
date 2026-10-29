package com.codementor.ai.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSkillResponseDTO {
    private Long id;
    private Long userId;
    private Long skillId;
    private String skillName;
    private String proficiency;
    private LocalDateTime updatedAt;
}
