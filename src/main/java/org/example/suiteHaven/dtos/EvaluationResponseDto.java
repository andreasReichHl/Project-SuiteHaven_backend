package org.example.suiteHaven.dtos;

public record EvaluationResponseDto(
        String username,
        double average,
        String evaluationText
) {
}
