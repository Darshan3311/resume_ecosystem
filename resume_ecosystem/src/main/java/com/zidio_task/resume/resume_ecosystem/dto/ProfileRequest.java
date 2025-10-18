package com.zidio_task.resume.resume_ecosystem.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String headline;
    private String summary;
    private String phone;
    private String email;

    @JsonAlias({"linkedin", "linkedIn"})
    private String linkedinUrl;

    @JsonAlias({"github", "gitHub"})
    private String githubUrl;

    @JsonAlias({"website", "portfolio"})
    private String portfolioUrl;

    @JsonAlias({"profileImage", "avatar", "photo"})
    private String profileImageUrl;

    @JsonAlias({"address", "city", "state", "country"})
    private String location;
}
