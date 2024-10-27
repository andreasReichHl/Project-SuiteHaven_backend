package org.example.suiteHaven.dtos;

public record EvaluationRequestDto(
        long flatId,
        int cleanness,
        int correctInformation,
        int checkIn,
        int location,
        int pricePerformanceRatio,
        int communication,
        String evaluationText

) {
}
