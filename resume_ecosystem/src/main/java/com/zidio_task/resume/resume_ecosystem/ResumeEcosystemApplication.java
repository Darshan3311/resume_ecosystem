package com.zidio_task.resume.resume_ecosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResumeEcosystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeEcosystemApplication.class, args);
		System.out.println("\n==============================================");
		System.out.println("Resume Builder Backend API is Running!");
		System.out.println("==============================================");
		System.out.println("API Base URL: http://localhost:8080/api");
		System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
		System.out.println("API Docs: http://localhost:8080/v3/api-docs");
		System.out.println("==============================================\n");
	}

}
