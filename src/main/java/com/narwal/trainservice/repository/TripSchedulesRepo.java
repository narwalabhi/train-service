package com.narwal.trainservice.repository;

import com.narwal.trainservice.model.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripSchedulesRepo extends MongoRepository<TripSchedule, String> {



}
