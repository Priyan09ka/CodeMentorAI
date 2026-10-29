package com.codementor.ai.repository;

import com.codementor.ai.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,Long> {
    List<Problem> findByDifficulty(Problem.Difficulty difficulty);
    Page<Problem> findByDifficulty(
            Problem.Difficulty difficulty,
            Pageable pageable
    );
}
