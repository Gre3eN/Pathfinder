package com.OutOfBounds.Pathfinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OutOfBounds.Pathfinder.model.PointOfInterest;

public interface PointOfInterestRepository extends MongoRepository<PointOfInterest, String>{

}
