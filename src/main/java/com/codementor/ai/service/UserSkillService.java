package com.codementor.ai.service;

import com.codementor.ai.dto.UserSkillRequestDTO;
import com.codementor.ai.dto.UserSkillResponseDTO;
import com.codementor.ai.entity.Skill;
import com.codementor.ai.entity.User;
import com.codementor.ai.entity.UserSkill;
import com.codementor.ai.exception.UserNotFoundException;
import com.codementor.ai.repository.SkillRepository;
import com.codementor.ai.repository.UserRepository;
import com.codementor.ai.repository.UserSkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public UserSkillService(
            UserSkillRepository userSkillRepository,
            SkillRepository skillRepository,
            UserRepository userRepository
    ){
        this.userSkillRepository=userSkillRepository;
        this.skillRepository=skillRepository;
        this.userRepository=userRepository;

    }

    @Transactional
    public UserSkillResponseDTO assignSkill(UserSkillRequestDTO request){
        User user=userRepository.findById(request.getUserId())
                .orElseThrow(()->new UserNotFoundException("User not found"));

        Skill skill=skillRepository.findById(request.getSkillId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Skill not found"));

        if(userSkillRepository.existsByUserIdAndSkillId(request.getUserId(), request.getSkillId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This skill is already assigned to the user");
        }

        UserSkill userSkill=new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill.setProficiency(request.getProficiency());

        UserSkill saved=userSkillRepository.save(userSkill);

        UserSkillResponseDTO response=new UserSkillResponseDTO();
        response.setId(saved.getId());
        response.setUserId(user.getId());
        response.setSkillId(skill.getId());
        response.setSkillName(skill.getName());
        response.setProficiency(saved.getProficiency());
        response.setUpdatedAt(saved.getUpdatedAt());

        return response;
    }

}
