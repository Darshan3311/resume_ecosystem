package com.zidio_task.resume.resume_ecosystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillRequest {
    @NotBlank(message = "Skills are required")
    private List<String> skills;
}

