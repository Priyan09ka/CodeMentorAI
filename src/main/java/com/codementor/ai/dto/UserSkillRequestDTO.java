package com.codementor.ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSkillRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long skillId;

    @NotBlank
    @Size(max = 30)
    private String proficiency="BEGINNER";


}
