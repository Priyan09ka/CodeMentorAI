package com.codementor.ai.dto;

public record AuthResponseDTO(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}