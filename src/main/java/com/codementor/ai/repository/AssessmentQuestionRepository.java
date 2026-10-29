package com.codementor.ai.repository;

import com.codementor.ai.entity.AssessmentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion,Long> {
    List<AssessmentQuestion> findByAssessmentIdOrderByPosition(Long assessmentId);
}
