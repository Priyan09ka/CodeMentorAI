package com.codementor.ai.controller;

import com.codementor.ai.dto.UserRequestDTO;
import com.codementor.ai.dto.UserResponseDTO;
import com.codementor.ai.entity.User;
import com.codementor.ai.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

   @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest){
        UserResponseDTO user= userService.createUser(userRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAll(){
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDTO user){
        return userService.updateUser(id ,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String >deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
