package com.codementor.ai.repository;

import com.codementor.ai.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission,Long> {
    List<Submission> findByUserIdOrderBySubmittedAtDesc(Long userId);
}
