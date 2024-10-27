package org.example.suiteHaven.repositories;

import org.example.suiteHaven.entities.booking.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
