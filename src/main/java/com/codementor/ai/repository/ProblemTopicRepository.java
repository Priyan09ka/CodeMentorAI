package com.codementor.ai.repository;

import com.codementor.ai.entity.ProblemTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemTopicRepository extends JpaRepository<ProblemTopic,Long> {
    List<ProblemTopic> findByProblemId(Long problemId);

}
