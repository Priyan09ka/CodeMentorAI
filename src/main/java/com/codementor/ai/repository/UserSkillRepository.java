package com.codementor.ai.repository;

import com.codementor.ai.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill,Long> {
    List<UserSkill> findByUserId(Long userId);
    boolean existsByUserIdAndSkillId(Long userId, Long skillId);
}
