package com.codementor.ai.controller;

import com.codementor.ai.entity.Skill;
import com.codementor.ai.repository.SkillRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository){
        this.skillRepository=skillRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Skill createSkill(@Valid @RequestBody CreateSkillRequest request){
        String name= request.name.trim();

        if (skillRepository.findByName(name).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Skill with this name already exists");
        }
        Skill skill=new Skill();
        skill.setName(name);
        skill.setDescription(
                request.description()==null?null:request.description().trim());
        return skillRepository.save(skill);
    }

    @GetMapping
    public List<Skill> getAllSkills(){
        return skillRepository.findAll();
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id){
        Skill skill=skillRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Skill not found"));
        return skillRepository.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id){
        if(!skillRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Skill not found");
        }
        skillRepository.deleteById(id);
    }


    public record CreateSkillRequest(@NotBlank @Size(max=100) String name,
                                      @Size(max=500) String description){ }
}
