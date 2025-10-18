package com.zidio_task.resume.resume_ecosystem.repository;

import com.zidio_task.resume.resume_ecosystem.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    Optional<User> findByWebhookSecret(String webhookSecret);
}

