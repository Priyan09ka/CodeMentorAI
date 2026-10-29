package com.codementor.ai.controller;

import com.codementor.ai.dto.UserSkillRequestDTO;
import com.codementor.ai.dto.UserSkillResponseDTO;
import com.codementor.ai.entity.UserSkill;
import com.codementor.ai.repository.UserSkillRepository;
import com.codementor.ai.service.UserSkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user-skills")
public class UserSkillController {

    private final UserSkillService userSkillService;
    private final UserSkillRepository userSkillRepository;
    public UserSkillController(UserSkillService userSkillService, UserSkillRepository userSkillRepository){
        this.userSkillService=userSkillService;
        this.userSkillRepository=userSkillRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserSkillResponseDTO assignSkill(@Valid @RequestBody UserSkillRequestDTO request){
        return userSkillService.assignSkill(request);
    }

    @GetMapping("/user/{userId}")
    public List<UserSkillResponseDTO> getUserSkills(@PathVariable Long userId){
        return userSkillRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();

    }
    @PutMapping("/{id}")
    public UserSkillResponseDTO updateProficiency(
            @PathVariable Long id,
            @RequestBody UpdateProficiencyRequest request) {

        UserSkill userSkill = userSkillRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server
                        .ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User skill not found"));

        if (request.proficiency() == null
                || request.proficiency().isBlank()
                || request.proficiency().length() > 30) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid proficiency");
        }

        userSkill.setProficiency(request.proficiency());
        return toResponse(userSkillRepository.save(userSkill));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserSkill(@PathVariable Long id) {

        if (!userSkillRepository.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User skill not found");
        }

        userSkillRepository.deleteById(id);
    }

    private UserSkillResponseDTO toResponse(UserSkill userSkill) {
        UserSkillResponseDTO response = new UserSkillResponseDTO();
        response.setId(userSkill.getId());
        response.setUserId(userSkill.getUser().getId());
        response.setSkillId(userSkill.getSkill().getId());
        response.setSkillName(userSkill.getSkill().getName());
        response.setProficiency(userSkill.getProficiency());
        response.setUpdatedAt(userSkill.getUpdatedAt());
        return response;
    }

    public record UpdateProficiencyRequest(String proficiency) {}
}