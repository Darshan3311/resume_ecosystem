package com.zidio_task.resume.resume_ecosystem.controller;

import com.zidio_task.resume.resume_ecosystem.dto.DTOs;
import com.zidio_task.resume.resume_ecosystem.entity.User;
import com.zidio_task.resume.resume_ecosystem.exception.UnauthorizedException;
import com.zidio_task.resume.resume_ecosystem.security.UserDetailsImpl;
import com.zidio_task.resume.resume_ecosystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Resume Management", description = "CRUD operations for resume components")
public class ResumeController {

    private final UserService userService;

    private void validateUserAccess(String userId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (!userDetails.getId().equals(userId)) {
            throw new UnauthorizedException("You don't have permission to access this resource");
        }
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get complete resume", description = "Retrieve entire resume for a user")
    public ResponseEntity<DTOs.ApiResponse<User>> getResume(
            @PathVariable String userId,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Resume retrieved successfully", user)
        );
    }

    // Profile Endpoints
    @PutMapping("/{userId}/profile")
    @Operation(summary = "Update profile", description = "Update user profile information")
    public ResponseEntity<DTOs.ApiResponse<User>> updateProfile(
            @PathVariable String userId,
            @Valid @RequestBody DTOs.ProfileRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.updateProfile(userId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Profile updated successfully", user)
        );
    }

    // Work Experience Endpoints
    @PostMapping("/{userId}/experience")
    @Operation(summary = "Add work experience", description = "Add new work experience entry")
    public ResponseEntity<DTOs.ApiResponse<User>> addWorkExperience(
            @PathVariable String userId,
            @Valid @RequestBody DTOs.WorkExperienceRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.addWorkExperience(userId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Work experience added successfully", user)
        );
    }

    @PutMapping("/{userId}/experience/{expId}")
    @Operation(summary = "Update work experience", description = "Update existing work experience entry")
    public ResponseEntity<DTOs.ApiResponse<User>> updateWorkExperience(
            @PathVariable String userId,
            @PathVariable String expId,
            @Valid @RequestBody DTOs.WorkExperienceRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.updateWorkExperience(userId, expId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Work experience updated successfully", user)
        );
    }

    @DeleteMapping("/{userId}/experience/{expId}")
    @Operation(summary = "Delete work experience", description = "Delete work experience entry")
    public ResponseEntity<DTOs.ApiResponse<User>> deleteWorkExperience(
            @PathVariable String userId,
            @PathVariable String expId,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.deleteWorkExperience(userId, expId);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Work experience deleted successfully", user)
        );
    }

    // Project Endpoints
    @PostMapping("/{userId}/projects")
    @Operation(summary = "Add project", description = "Add new project entry")
    public ResponseEntity<DTOs.ApiResponse<User>> addProject(
            @PathVariable String userId,
            @Valid @RequestBody DTOs.ProjectRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.addProject(userId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Project added successfully", user)
        );
    }

    @PutMapping("/{userId}/projects/{projectId}")
    @Operation(summary = "Update project", description = "Update existing project entry")
    public ResponseEntity<DTOs.ApiResponse<User>> updateProject(
            @PathVariable String userId,
            @PathVariable String projectId,
            @Valid @RequestBody DTOs.ProjectRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.updateProject(userId, projectId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Project updated successfully", user)
        );
    }

    @DeleteMapping("/{userId}/projects/{projectId}")
    @Operation(summary = "Delete project", description = "Delete project entry")
    public ResponseEntity<DTOs.ApiResponse<User>> deleteProject(
            @PathVariable String userId,
            @PathVariable String projectId,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.deleteProject(userId, projectId);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Project deleted successfully", user)
        );
    }

    // Education Endpoints
    @PostMapping("/{userId}/education")
    @Operation(summary = "Add education", description = "Add new education entry")
    public ResponseEntity<DTOs.ApiResponse<User>> addEducation(
            @PathVariable String userId,
            @Valid @RequestBody DTOs.EducationRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.addEducation(userId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Education added successfully", user)
        );
    }

    @PutMapping("/{userId}/education/{eduId}")
    @Operation(summary = "Update education", description = "Update existing education entry")
    public ResponseEntity<DTOs.ApiResponse<User>> updateEducation(
            @PathVariable String userId,
            @PathVariable String eduId,
            @Valid @RequestBody DTOs.EducationRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.updateEducation(userId, eduId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Education updated successfully", user)
        );
    }

    @DeleteMapping("/{userId}/education/{eduId}")
    @Operation(summary = "Delete education", description = "Delete education entry")
    public ResponseEntity<DTOs.ApiResponse<User>> deleteEducation(
            @PathVariable String userId,
            @PathVariable String eduId,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.deleteEducation(userId, eduId);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Education deleted successfully", user)
        );
    }

    // Certification Endpoints
    @PostMapping("/{userId}/certifications")
    @Operation(summary = "Add certification", description = "Add new certification entry")
    public ResponseEntity<DTOs.ApiResponse<User>> addCertification(
            @PathVariable String userId,
            @Valid @RequestBody DTOs.CertificationRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.addCertification(userId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Certification added successfully", user)
        );
    }

    @PutMapping("/{userId}/certifications/{certId}")
    @Operation(summary = "Update certification", description = "Update existing certification entry")
    public ResponseEntity<DTOs.ApiResponse<User>> updateCertification(
            @PathVariable String userId,
            @PathVariable String certId,
            @Valid @RequestBody DTOs.CertificationRequest request,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.updateCertification(userId, certId, request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Certification updated successfully", user)
        );
    }

    @DeleteMapping("/{userId}/certifications/{certId}")
    @Operation(summary = "Delete certification", description = "Delete certification entry")
    public ResponseEntity<DTOs.ApiResponse<User>> deleteCertification(
            @PathVariable String userId,
            @PathVariable String certId,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.deleteCertification(userId, certId);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Certification deleted successfully", user)
        );
    }

    // Skills Endpoints
    @PutMapping("/{userId}/skills")
    @Operation(summary = "Update skills", description = "Replace all skills with new list")
    public ResponseEntity<DTOs.ApiResponse<User>> updateSkills(
            @PathVariable String userId,
            @RequestBody List<String> skills,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.updateSkills(userId, skills);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Skills updated successfully", user)
        );
    }

    @PostMapping("/{userId}/skills")
    @Operation(summary = "Add skill", description = "Add a single skill")
    public ResponseEntity<DTOs.ApiResponse<User>> addSkill(
            @PathVariable String userId,
            @RequestBody String skill,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.addSkill(userId, skill);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Skill added successfully", user)
        );
    }

    @DeleteMapping("/{userId}/skills/{skill}")
    @Operation(summary = "Delete skill", description = "Remove a single skill")
    public ResponseEntity<DTOs.ApiResponse<User>> deleteSkill(
            @PathVariable String userId,
            @PathVariable String skill,
            Authentication authentication) {

        validateUserAccess(userId, authentication);
        User user = userService.deleteSkill(userId, skill);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Skill deleted successfully", user)
        );
    }
}

