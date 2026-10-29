package com.codementor.ai.controller;

import com.codementor.ai.dto.AuthResponseDTO;
import com.codementor.ai.dto.LoginRequestDTO;
import com.codementor.ai.jwt.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final long expiresIn;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            @Value("${codementor.jwt.expiration-ms}") long expiresIn
    ){
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
        this.expiresIn=expiresIn;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.email(),
                                    request.password()));
            String token = jwtService.generateToken(authentication.getName());

            return new AuthResponseDTO(token, "Bearer", expiresIn / 1000);
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
