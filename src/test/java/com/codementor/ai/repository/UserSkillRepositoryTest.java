package com.codementor.ai.repository;

import com.codementor.ai.CacheTestConfig;
import com.codementor.ai.entity.Skill;
import com.codementor.ai.entity.User;
import com.codementor.ai.entity.UserSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(CacheTestConfig.class)
public class UserSkillRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void shouldSaveForUser() {
        // 1. Save the user first
        User user = new User();
        user.setName("Test User");
        user.setEmail("skilltest2@example.com");
        user.setPassword("test-password");

        User savedUser = userRepository.saveAndFlush(user);

        // 2. Save the skill first
        Skill skill = new Skill(
                "Test Java 2",
                "Testing user skill relationship"
        );

        Skill savedSkill = skillRepository.saveAndFlush(skill);

        // 3. Connect the saved user and saved skill
        UserSkill userSkills = new UserSkill();
        userSkills.setUser(savedUser);
        userSkills.setSkill(savedSkill);
        userSkills.setProficiency("INTERMEDIATE");

        UserSkill savedUserSkills =
                userSkillRepository.saveAndFlush(userSkills);

        // 4. Verify the relationship
        assertThat(savedUserSkills.getId()).isNotNull();

        assertThat(userSkillRepository.findByUserId(savedUser.getId()))
                .hasSize(1);
    }


    @Test
    void shouldFindMultipleSkillsForUser() {
        User user = new User();
        user.setName("Multi Skill User");
        user.setEmail("multiskill@example.com");
        user.setPassword("test-password");

        User savedUser = userRepository.saveAndFlush(user);

        Skill java = skillRepository.saveAndFlush(
                new Skill("Java Multi Test", "Java programming")
        );

        Skill sql = skillRepository.saveAndFlush(
                new Skill("SQL Multi Test", "SQL database")
        );

        UserSkill javaSkill = new UserSkill();
        javaSkill.setUser(savedUser);
        javaSkill.setSkill(java);
        javaSkill.setProficiency("ADVANCED");
        userSkillRepository.saveAndFlush(javaSkill);

        UserSkill sqlSkill = new UserSkill();
        sqlSkill.setUser(savedUser);
        sqlSkill.setSkill(sql);
        sqlSkill.setProficiency("INTERMEDIATE");
        userSkillRepository.saveAndFlush(sqlSkill);

        assertThat(
                userSkillRepository.findByUserId(savedUser.getId())
        ).hasSize(2);
    }

}