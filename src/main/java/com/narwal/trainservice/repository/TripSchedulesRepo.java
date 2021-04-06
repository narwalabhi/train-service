package com.narwal.trainservice.repository;

import com.narwal.trainservice.model.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripSchedulesRepo extends MongoRepository<TripSchedule, String> {

    public void deleteByTripId(String tripId);

    public TripSchedule findByTripId(String tipID);

    public Optional<TripSchedule> findById(String tipScheduleID);
}
