package com.zidio_task.resume.resume_ecosystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Set<String> roles = new HashSet<>();

    private String webhookSecret = UUID.randomUUID().toString();

    private Profile profile = new Profile();

    private List<WorkExperience> workExperience = new ArrayList<>();

    private List<Project> projects = new ArrayList<>();

    private List<Education> education = new ArrayList<>();

    private List<String> skills = new ArrayList<>();

    private List<Certification> certifications = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private boolean active = true;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Profile {
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkExperience {
        private String id = UUID.randomUUID().toString();
        private String jobTitle;
        private String company;
        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private boolean currentlyWorking;
        private String description;
        private List<String> achievements = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Project {
        private String id = UUID.randomUUID().toString();
        private String projectName;
        private String description;
        private List<String> technologies = new ArrayList<>();
        private String projectUrl;
        private String sourceCodeUrl;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<String> achievements = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Education {
        private String id = UUID.randomUUID().toString();
        private String degree;
        private String fieldOfStudy;
        private String institution;
        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Double gpa;
        private String description;
        private List<String> achievements = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Certification {
        private String id = UUID.randomUUID().toString();
        private String name;
        private String issuingOrganization;
        private LocalDateTime issueDate;
        private LocalDateTime expiryDate;
        private String credentialId;
        private String credentialUrl;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
    }
}

