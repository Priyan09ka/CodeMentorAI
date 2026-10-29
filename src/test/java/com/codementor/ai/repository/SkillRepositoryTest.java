
package com.codementor.ai.repository;

import com.codementor.ai.CacheTestConfig;
import com.codementor.ai.entity.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Import(CacheTestConfig.class)
class SkillRepositoryTest {

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void shouldSaveAndFindSkill() {
        Skill skill = new Skill(
                "Java",
                "Core Java Programming"
        );

        skillRepository.save(skill);

        assertThat(skillRepository.findByName("Java"))
                .isPresent();
    }


    @Test
    void shouldRejectDuplicateSkillNames() {
        Skill first = new Skill(
                "Java",
                "Core Java Programming"
        );
        skillRepository.saveAndFlush(first);

        Skill duplicate = new Skill(
                "Java",
                "Duplicate Java skill"
        );

        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> skillRepository.saveAndFlush(duplicate)
        );
    }
}
