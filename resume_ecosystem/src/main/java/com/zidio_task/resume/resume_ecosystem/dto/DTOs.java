package com.zidio_task.resume.resume_ecosystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class DTOs {

    // Authentication DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;

        private String firstName;
        private String lastName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private String userId;
        private String email;
        private String firstName;
        private String lastName;
        private String webhookSecret;
    }

    // Profile DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileRequest {
        private String firstName;
        private String lastName;
        private String headline;
        private String summary;
        private String phone;
        private String email;
        private String linkedinUrl;
        private String githubUrl;
        private String portfolioUrl;
        private String profileImageUrl;
        private String location;
    }

    // Work Experience DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkExperienceRequest {
        @NotBlank(message = "Job title is required")
        private String jobTitle;

        @NotBlank(message = "Company is required")
        private String company;

        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private boolean currentlyWorking;
        private String description;
        private List<String> achievements;
    }

    // Project DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectRequest {
        @NotBlank(message = "Project name is required")
        private String projectName;

        private String description;
        private List<String> technologies;
        private String projectUrl;
        private String sourceCodeUrl;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<String> achievements;
    }

    // Education DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EducationRequest {
        @NotBlank(message = "Degree is required")
        private String degree;

        private String fieldOfStudy;

        @NotBlank(message = "Institution is required")
        private String institution;

        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Double gpa;
        private String description;
        private List<String> achievements;
    }

    // Certification DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CertificationRequest {
        @NotBlank(message = "Certification name is required")
        private String name;

        @NotBlank(message = "Issuing organization is required")
        private String issuingOrganization;

        private LocalDateTime issueDate;
        private LocalDateTime expiryDate;
        private String credentialId;
        private String credentialUrl;
    }

    // Skill DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillRequest {
        @NotBlank(message = "Skills are required")
        private List<String> skills;
    }

    // Webhook DTOs
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WebhookRequest {
        @NotBlank(message = "User ID is required")
        private String userId;

        @NotBlank(message = "Achievement type is required")
        private String achievementType; // "project", "experience", "certification", "education", "skill"

        @NotBlank(message = "Achievement data is required")
        private String achievementData; // JSON string

        @NotBlank(message = "Webhook secret is required")
        private String webhookSecret;

        private LocalDateTime timestamp = LocalDateTime.now();
    }

    // Generic Response DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
        private LocalDateTime timestamp = LocalDateTime.now();

        public ApiResponse(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
            this.timestamp = LocalDateTime.now();
        }
    }

    // Error Response DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private boolean success = false;
        private String message;
        private String error;
        private LocalDateTime timestamp = LocalDateTime.now();

        public ErrorResponse(String message, String error) {
            this.message = message;
            this.error = error;
            this.timestamp = LocalDateTime.now();
        }
    }
}

