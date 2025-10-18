package com.zidio_task.resume.resume_ecosystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookRequest {
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

