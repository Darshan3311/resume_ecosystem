package com.zidio_task.resume.resume_ecosystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequest {
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

