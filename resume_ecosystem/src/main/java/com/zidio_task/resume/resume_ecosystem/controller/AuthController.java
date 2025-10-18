package com.zidio_task.resume.resume_ecosystem.controller;

import com.zidio_task.resume.resume_ecosystem.dto.DTOs;
import com.zidio_task.resume.resume_ecosystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User authentication and registration endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account with email and password")
    public ResponseEntity<DTOs.ApiResponse<DTOs.AuthResponse>> register(
            @Valid @RequestBody DTOs.RegisterRequest request) {

        DTOs.AuthResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new DTOs.ApiResponse<>(true, "User registered successfully", response)
        );
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate user and receive JWT token")
    public ResponseEntity<DTOs.ApiResponse<DTOs.AuthResponse>> login(
            @Valid @RequestBody DTOs.LoginRequest request) {

        DTOs.AuthResponse response = authService.login(request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Login successful", response)
        );
    }
}

