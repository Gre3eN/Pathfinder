package com.OutOfBounds.Pathfinder.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OutOfBounds.Pathfinder.model.ApplicationUser;

public interface UserRepostirory extends MongoRepository<ApplicationUser, String> {

	Optional<ApplicationUser> findByUsername(String username);

}
