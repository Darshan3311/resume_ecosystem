package com.zidio_task.resume.resume_ecosystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zidio_task.resume.resume_ecosystem.dto.DTOs;
import com.zidio_task.resume.resume_ecosystem.entity.User;
import com.zidio_task.resume.resume_ecosystem.exception.UnauthorizedException;
import com.zidio_task.resume.resume_ecosystem.exception.ValidationException;
import com.zidio_task.resume.resume_ecosystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebhookService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public User processAchievement(DTOs.WebhookRequest request) {
        // Verify webhook secret
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ValidationException("User not found"));

        if (!user.getWebhookSecret().equals(request.getWebhookSecret())) {
            throw new UnauthorizedException("Invalid webhook secret");
        }

        // Process based on achievement type
        try {
            switch (request.getAchievementType().toLowerCase()) {
                case "project":
                    return addProjectFromWebhook(user, request.getAchievementData());
                case "experience":
                case "work_experience":
                    return addWorkExperienceFromWebhook(user, request.getAchievementData());
                case "certification":
                    return addCertificationFromWebhook(user, request.getAchievementData());
                case "education":
                    return addEducationFromWebhook(user, request.getAchievementData());
                case "skill":
                case "skills":
                    return addSkillsFromWebhook(user, request.getAchievementData());
                case "profile":
                case "profile_update":
                    return updateProfileFromWebhook(user, request.getAchievementData());
                default:
                    throw new ValidationException("Invalid achievement type: " + request.getAchievementType());
            }
        } catch (Exception e) {
            throw new ValidationException("Failed to process achievement data: " + e.getMessage());
        }
    }

    private User addProjectFromWebhook(User user, String achievementData) throws Exception {
        DTOs.ProjectRequest projectRequest = objectMapper.readValue(achievementData, DTOs.ProjectRequest.class);

        User.Project project = new User.Project();
        project.setId(UUID.randomUUID().toString());
        project.setProjectName(projectRequest.getProjectName());
        project.setDescription(projectRequest.getDescription());
        project.setTechnologies(projectRequest.getTechnologies());
        project.setProjectUrl(projectRequest.getProjectUrl());
        project.setSourceCodeUrl(projectRequest.getSourceCodeUrl());
        project.setStartDate(projectRequest.getStartDate());
        project.setEndDate(projectRequest.getEndDate());
        project.setAchievements(projectRequest.getAchievements());
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());

        user.getProjects().add(project);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    private User addWorkExperienceFromWebhook(User user, String achievementData) throws Exception {
        DTOs.WorkExperienceRequest expRequest = objectMapper.readValue(achievementData, DTOs.WorkExperienceRequest.class);

        User.WorkExperience experience = new User.WorkExperience();
        experience.setId(UUID.randomUUID().toString());
        experience.setJobTitle(expRequest.getJobTitle());
        experience.setCompany(expRequest.getCompany());
        experience.setLocation(expRequest.getLocation());
        experience.setStartDate(expRequest.getStartDate());
        experience.setEndDate(expRequest.getEndDate());
        experience.setCurrentlyWorking(expRequest.isCurrentlyWorking());
        experience.setDescription(expRequest.getDescription());
        experience.setAchievements(expRequest.getAchievements());
        experience.setCreatedAt(LocalDateTime.now());
        experience.setUpdatedAt(LocalDateTime.now());

        user.getWorkExperience().add(experience);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    private User addCertificationFromWebhook(User user, String achievementData) throws Exception {
        DTOs.CertificationRequest certRequest = objectMapper.readValue(achievementData, DTOs.CertificationRequest.class);

        User.Certification certification = new User.Certification();
        certification.setId(UUID.randomUUID().toString());
        certification.setName(certRequest.getName());
        certification.setIssuingOrganization(certRequest.getIssuingOrganization());
        certification.setIssueDate(certRequest.getIssueDate());
        certification.setExpiryDate(certRequest.getExpiryDate());
        certification.setCredentialId(certRequest.getCredentialId());
        certification.setCredentialUrl(certRequest.getCredentialUrl());
        certification.setCreatedAt(LocalDateTime.now());
        certification.setUpdatedAt(LocalDateTime.now());

        user.getCertifications().add(certification);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    private User addEducationFromWebhook(User user, String achievementData) throws Exception {
        DTOs.EducationRequest eduRequest = objectMapper.readValue(achievementData, DTOs.EducationRequest.class);

        User.Education education = new User.Education();
        education.setId(UUID.randomUUID().toString());
        education.setDegree(eduRequest.getDegree());
        education.setFieldOfStudy(eduRequest.getFieldOfStudy());
        education.setInstitution(eduRequest.getInstitution());
        education.setLocation(eduRequest.getLocation());
        education.setStartDate(eduRequest.getStartDate());
        education.setEndDate(eduRequest.getEndDate());
        education.setGpa(eduRequest.getGpa());
        education.setDescription(eduRequest.getDescription());
        education.setAchievements(eduRequest.getAchievements());
        education.setCreatedAt(LocalDateTime.now());
        education.setUpdatedAt(LocalDateTime.now());

        user.getEducation().add(education);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    private User addSkillsFromWebhook(User user, String achievementData) throws Exception {
        DTOs.SkillRequest skillRequest = objectMapper.readValue(achievementData, DTOs.SkillRequest.class);

        for (String skill : skillRequest.getSkills()) {
            if (!user.getSkills().contains(skill)) {
                user.getSkills().add(skill);
            }
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    private User updateProfileFromWebhook(User user, String achievementData) throws Exception {
        DTOs.ProfileRequest profileRequest = objectMapper.readValue(achievementData, DTOs.ProfileRequest.class);

        User.Profile profile = user.getProfile();
        if (profileRequest.getFirstName() != null) profile.setFirstName(profileRequest.getFirstName());
        if (profileRequest.getLastName() != null) profile.setLastName(profileRequest.getLastName());
        if (profileRequest.getHeadline() != null) profile.setHeadline(profileRequest.getHeadline());
        if (profileRequest.getSummary() != null) profile.setSummary(profileRequest.getSummary());
        if (profileRequest.getPhone() != null) profile.setPhone(profileRequest.getPhone());
        if (profileRequest.getEmail() != null) profile.setEmail(profileRequest.getEmail());
        if (profileRequest.getLinkedinUrl() != null) profile.setLinkedinUrl(profileRequest.getLinkedinUrl());
        if (profileRequest.getGithubUrl() != null) profile.setGithubUrl(profileRequest.getGithubUrl());
        if (profileRequest.getPortfolioUrl() != null) profile.setPortfolioUrl(profileRequest.getPortfolioUrl());
        if (profileRequest.getProfileImageUrl() != null) profile.setProfileImageUrl(profileRequest.getProfileImageUrl());
        if (profileRequest.getLocation() != null) profile.setLocation(profileRequest.getLocation());

        user.setProfile(profile);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
