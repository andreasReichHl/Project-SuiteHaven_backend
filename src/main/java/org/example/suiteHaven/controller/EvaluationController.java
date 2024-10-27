package org.example.suiteHaven.controller;

import org.example.suiteHaven.dtos.EvaluationRequestDto;
import org.example.suiteHaven.dtos.EvaluationResponseDto;
import org.example.suiteHaven.services.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${suiteHaven.api.path}" +"/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<EvaluationResponseDto> newEvaluation(@RequestBody EvaluationRequestDto dto, Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationService.newEvaluation(dto,authentication));
    }

    @GetMapping("/{evaId}")
    public ResponseEntity<EvaluationResponseDto> getEvaluation(@PathVariable Long evaId){
        return ResponseEntity.ok(evaluationService.getEvaluation(evaId));
    }

    @DeleteMapping("/{evaId}")
    public ResponseEntity<HttpStatus> deleteEvaluation(@PathVariable Long evaId){
        evaluationService.deleteEvaluation(evaId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
