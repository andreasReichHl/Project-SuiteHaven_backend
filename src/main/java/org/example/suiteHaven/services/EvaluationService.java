package org.example.suiteHaven.services;

import org.example.suiteHaven.dtos.EvaluationRequestDto;
import org.example.suiteHaven.dtos.EvaluationResponseDto;
import org.example.suiteHaven.entities.booking.Evaluation;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.repositories.EvaluationRepository;
import org.example.suiteHaven.repositories.HolidayFlatRepository;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final HolidayFlatRepository holidayFlatRepository;

    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository, HolidayFlatRepository holidayFlatRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.holidayFlatRepository = holidayFlatRepository;
    }

    // new evaluation
    public EvaluationResponseDto newEvaluation(EvaluationRequestDto dto, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new NoSuchElementException("User not found"));
        HolidayFlat holidayFlat = holidayFlatRepository.findById(dto.flatId()).orElseThrow(()->new NoSuchElementException("Flat not found"));

        if (user.getId() != holidayFlat.getUser().getId()){
            Evaluation evaluation = createEvaluationFromUser(dto, user, holidayFlat);
            evaluationRepository.save(evaluation);
            holidayFlatRepository.save(holidayFlat);
            return new EvaluationResponseDto(evaluation.getUser().getUsername(), evaluation.getAverageStars(), evaluation.getEvaluationText());
        }else{
            throw new IllegalStateException("Owner cannot review their own holiday flat");
        }
    }

    private static Evaluation createEvaluationFromUser(EvaluationRequestDto dto, User user, HolidayFlat holidayFlat) {
        Evaluation evaluation = new Evaluation();
        evaluation.setCleanness(dto.cleanness());
        evaluation.setCheckIn(dto.checkIn());
        evaluation.setLocation(dto.location());
        evaluation.setPricePerformanceRatio(dto.pricePerformanceRatio());
        evaluation.setCorrectInformation(dto.correctInformation());
        evaluation.setCommunication(dto.communication());
        evaluation.setEvaluationText(dto.evaluationText());
        evaluation.setUser(user);
        holidayFlat.getManagement().getEvaluations().add(evaluation);
        return evaluation;
    }

    // get evaluation
    public EvaluationResponseDto getEvaluation(long evaluationId){
        Evaluation evaluation = evaluationRepository.findById(evaluationId).orElseThrow(()-> new NoSuchElementException("Evaluation not found"));
        return new EvaluationResponseDto(evaluation.getUser().getUsername(), evaluation.getAverageStars(), evaluation.getEvaluationText());
    }

    // delete evaluation
    public void deleteEvaluation(long evaluationId){
        try{
            evaluationRepository.deleteById(evaluationId);
        } catch (Exception e) {
            throw new NoSuchElementException("Evaluation not found");
        }
    }
}
