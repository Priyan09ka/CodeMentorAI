package com.codementor.ai.service;

import com.codementor.ai.dto.UserRequestDTO;
import com.codementor.ai.dto.UserResponseDTO;
import com.codementor.ai.entity.User;
import com.codementor.ai.exception.EmailAlreadyExistException;
import com.codementor.ai.exception.UserNotFoundException;
import com.codementor.ai.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user=new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        if (userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistException("Email already exists");
        }
        User savedUser=userRepository.save(user);
        return toUserResponseDTO(savedUser);
    }

    @Cacheable(value = "users",key = "#id")
    public UserResponseDTO getUserById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        return toUserResponseDTO(user);
    }

    public List<UserResponseDTO> getAll(){
        return userRepository.findAll()
                .stream()
                .map(this::toUserResponseDTO)
                .toList();
    }

    @CachePut(value = "users",key = "#id")
    public UserResponseDTO updateUser(Long id,UserRequestDTO updatedUser){
        User existingUser=userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        if(!existingUser.getEmail().equalsIgnoreCase(updatedUser.getEmail())
        && userRepository.existsByEmail(updatedUser.getEmail())){
            throw new EmailAlreadyExistException("Email already exists");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        User updated=userRepository.save(existingUser);
        return toUserResponseDTO(updated);
    }

    @CacheEvict(value = "users",key = "#id")
    public void deleteUser(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    private UserResponseDTO toUserResponseDTO(User user){
        UserResponseDTO response=new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;


    }
}
