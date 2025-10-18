package com.zidio_task.resume.resume_ecosystem.service;

import com.zidio_task.resume.resume_ecosystem.dto.ProfileRequest;
import com.zidio_task.resume.resume_ecosystem.dto.WorkExperienceRequest;
import com.zidio_task.resume.resume_ecosystem.dto.ProjectRequest;
import com.zidio_task.resume.resume_ecosystem.dto.EducationRequest;
import com.zidio_task.resume.resume_ecosystem.dto.CertificationRequest;
import com.zidio_task.resume.resume_ecosystem.entity.User;
import com.zidio_task.resume.resume_ecosystem.exception.ResourceNotFoundException;
import com.zidio_task.resume.resume_ecosystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    // Profile Operations
    public User updateProfile(String userId, ProfileRequest request) {
        User user = getUserById(userId);

        User.Profile profile = user.getProfile();
        if (request.getFirstName() != null) profile.setFirstName(request.getFirstName());
        if (request.getLastName() != null) profile.setLastName(request.getLastName());
        if (request.getHeadline() != null) profile.setHeadline(request.getHeadline());
        if (request.getSummary() != null) profile.setSummary(request.getSummary());
        if (request.getPhone() != null) profile.setPhone(request.getPhone());
        if (request.getEmail() != null) profile.setEmail(request.getEmail());
        if (request.getLinkedinUrl() != null) profile.setLinkedinUrl(request.getLinkedinUrl());
        if (request.getGithubUrl() != null) profile.setGithubUrl(request.getGithubUrl());
        if (request.getPortfolioUrl() != null) profile.setPortfolioUrl(request.getPortfolioUrl());
        if (request.getProfileImageUrl() != null) profile.setProfileImageUrl(request.getProfileImageUrl());
        if (request.getLocation() != null) profile.setLocation(request.getLocation());

        user.setProfile(profile);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    // Work Experience Operations
    public User addWorkExperience(String userId, WorkExperienceRequest request) {
        User user = getUserById(userId);

        User.WorkExperience experience = new User.WorkExperience();
        experience.setId(UUID.randomUUID().toString());
        experience.setJobTitle(request.getJobTitle());
        experience.setCompany(request.getCompany());
        experience.setLocation(request.getLocation());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());
        experience.setCurrentlyWorking(request.isCurrentlyWorking());
        experience.setDescription(request.getDescription());
        experience.setAchievements(request.getAchievements() != null ? request.getAchievements() : new ArrayList<>());
        experience.setCreatedAt(LocalDateTime.now());
        experience.setUpdatedAt(LocalDateTime.now());

        user.getWorkExperience().add(experience);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateWorkExperience(String userId, String expId, WorkExperienceRequest request) {
        User user = getUserById(userId);

        User.WorkExperience experience = user.getWorkExperience().stream()
                .filter(exp -> exp.getId().equals(expId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Work experience not found with id: " + expId));

        if (request.getJobTitle() != null) experience.setJobTitle(request.getJobTitle());
        if (request.getCompany() != null) experience.setCompany(request.getCompany());
        if (request.getLocation() != null) experience.setLocation(request.getLocation());
        if (request.getStartDate() != null) experience.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) experience.setEndDate(request.getEndDate());
        experience.setCurrentlyWorking(request.isCurrentlyWorking());
        if (request.getDescription() != null) experience.setDescription(request.getDescription());
        if (request.getAchievements() != null) experience.setAchievements(request.getAchievements());
        experience.setUpdatedAt(LocalDateTime.now());

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User deleteWorkExperience(String userId, String expId) {
        User user = getUserById(userId);

        boolean removed = user.getWorkExperience().removeIf(exp -> exp.getId().equals(expId));
        if (!removed) {
            throw new ResourceNotFoundException("Work experience not found with id: " + expId);
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Project Operations
    public User addProject(String userId, ProjectRequest request) {
        User user = getUserById(userId);

        User.Project project = new User.Project();
        project.setId(UUID.randomUUID().toString());
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setTechnologies(request.getTechnologies() != null ? request.getTechnologies() : new ArrayList<>());
        project.setProjectUrl(request.getProjectUrl());
        project.setSourceCodeUrl(request.getSourceCodeUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setAchievements(request.getAchievements() != null ? request.getAchievements() : new ArrayList<>());
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());

        user.getProjects().add(project);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateProject(String userId, String projectId, ProjectRequest request) {
        User user = getUserById(userId);

        User.Project project = user.getProjects().stream()
                .filter(p -> p.getId().equals(projectId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (request.getProjectName() != null) project.setProjectName(request.getProjectName());
        if (request.getDescription() != null) project.setDescription(request.getDescription());
        if (request.getTechnologies() != null) project.setTechnologies(request.getTechnologies());
        if (request.getProjectUrl() != null) project.setProjectUrl(request.getProjectUrl());
        if (request.getSourceCodeUrl() != null) project.setSourceCodeUrl(request.getSourceCodeUrl());
        if (request.getStartDate() != null) project.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) project.setEndDate(request.getEndDate());
        if (request.getAchievements() != null) project.setAchievements(request.getAchievements());
        project.setUpdatedAt(LocalDateTime.now());

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User deleteProject(String userId, String projectId) {
        User user = getUserById(userId);

        boolean removed = user.getProjects().removeIf(p -> p.getId().equals(projectId));
        if (!removed) {
            throw new ResourceNotFoundException("Project not found with id: " + projectId);
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Education Operations
    public User addEducation(String userId, EducationRequest request) {
        User user = getUserById(userId);

        User.Education education = new User.Education();
        education.setId(UUID.randomUUID().toString());
        education.setDegree(request.getDegree());
        education.setFieldOfStudy(request.getFieldOfStudy());
        education.setInstitution(request.getInstitution());
        education.setLocation(request.getLocation());
        education.setStartDate(request.getStartDate());
        education.setEndDate(request.getEndDate());
        education.setGpa(request.getGpa());
        education.setDescription(request.getDescription());
        education.setAchievements(request.getAchievements() != null ? request.getAchievements() : new ArrayList<>());
        education.setCreatedAt(LocalDateTime.now());
        education.setUpdatedAt(LocalDateTime.now());

        user.getEducation().add(education);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateEducation(String userId, String eduId, EducationRequest request) {
        User user = getUserById(userId);

        User.Education education = user.getEducation().stream()
                .filter(e -> e.getId().equals(eduId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + eduId));

        if (request.getDegree() != null) education.setDegree(request.getDegree());
        if (request.getFieldOfStudy() != null) education.setFieldOfStudy(request.getFieldOfStudy());
        if (request.getInstitution() != null) education.setInstitution(request.getInstitution());
        if (request.getLocation() != null) education.setLocation(request.getLocation());
        if (request.getStartDate() != null) education.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) education.setEndDate(request.getEndDate());
        if (request.getGpa() != null) education.setGpa(request.getGpa());
        if (request.getDescription() != null) education.setDescription(request.getDescription());
        if (request.getAchievements() != null) education.setAchievements(request.getAchievements());
        education.setUpdatedAt(LocalDateTime.now());

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User deleteEducation(String userId, String eduId) {
        User user = getUserById(userId);

        boolean removed = user.getEducation().removeIf(e -> e.getId().equals(eduId));
        if (!removed) {
            throw new ResourceNotFoundException("Education not found with id: " + eduId);
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Certification Operations
    public User addCertification(String userId, CertificationRequest request) {
        User user = getUserById(userId);

        User.Certification certification = new User.Certification();
        certification.setId(UUID.randomUUID().toString());
        certification.setName(request.getName());
        certification.setIssuingOrganization(request.getIssuingOrganization());
        certification.setIssueDate(request.getIssueDate());
        certification.setExpiryDate(request.getExpiryDate());
        certification.setCredentialId(request.getCredentialId());
        certification.setCredentialUrl(request.getCredentialUrl());
        certification.setCreatedAt(LocalDateTime.now());
        certification.setUpdatedAt(LocalDateTime.now());

        user.getCertifications().add(certification);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateCertification(String userId, String certId, CertificationRequest request) {
        User user = getUserById(userId);

        User.Certification certification = user.getCertifications().stream()
                .filter(c -> c.getId().equals(certId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Certification not found with id: " + certId));

        if (request.getName() != null) certification.setName(request.getName());
        if (request.getIssuingOrganization() != null) certification.setIssuingOrganization(request.getIssuingOrganization());
        if (request.getIssueDate() != null) certification.setIssueDate(request.getIssueDate());
        if (request.getExpiryDate() != null) certification.setExpiryDate(request.getExpiryDate());
        if (request.getCredentialId() != null) certification.setCredentialId(request.getCredentialId());
        if (request.getCredentialUrl() != null) certification.setCredentialUrl(request.getCredentialUrl());
        certification.setUpdatedAt(LocalDateTime.now());

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User deleteCertification(String userId, String certId) {
        User user = getUserById(userId);

        boolean removed = user.getCertifications().removeIf(c -> c.getId().equals(certId));
        if (!removed) {
            throw new ResourceNotFoundException("Certification not found with id: " + certId);
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Skills Operations
    public User updateSkills(String userId, List<String> skills) {
        User user = getUserById(userId);
        user.setSkills(skills);
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User addSkill(String userId, String skill) {
        User user = getUserById(userId);
        if (!user.getSkills().contains(skill)) {
            user.getSkills().add(skill);
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        }
        return user;
    }

    public User deleteSkill(String userId, String skill) {
        User user = getUserById(userId);
        user.getSkills().remove(skill);
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}
