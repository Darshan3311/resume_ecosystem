package com.zidio_task.resume.resume_ecosystem.controller;

import com.zidio_task.resume.resume_ecosystem.dto.DTOs;
import com.zidio_task.resume.resume_ecosystem.entity.User;
import com.zidio_task.resume.resume_ecosystem.service.WebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hooks")
@RequiredArgsConstructor
@Tag(name = "Webhook Integration", description = "External platform integration endpoints")
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping("/achievement")
    @Operation(
        summary = "Process external achievement",
        description = "Secure endpoint for external platforms to add achievements to user profiles. " +
                     "Requires valid webhook secret for authentication. " +
                     "Supports: projects, work_experience, certifications, education, skills"
    )
    public ResponseEntity<DTOs.ApiResponse<User>> processAchievement(
            @Valid @RequestBody DTOs.WebhookRequest request) {

        User user = webhookService.processAchievement(request);

        return ResponseEntity.ok(
                new DTOs.ApiResponse<>(true, "Achievement processed successfully", user)
        );
    }
}

