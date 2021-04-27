package com.narwal.trainservice.repository;

import com.narwal.trainservice.model.Trip;
import com.narwal.trainservice.model.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripSchedulesRepo extends MongoRepository<TripSchedule, String> {

    void deleteById(String tripId);

    TripSchedule findByTripId(String tipID);

    Optional<TripSchedule> findById(String tipScheduleID);

    @Query("{$and:[{'tripId': ?0}, {'tripDate': ?1}]}")
    TripSchedule findByTripIdAndTripDate(String tripId, LocalDate date);

    TripSchedule findTripScheduleByTripIdAndTripDate(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date);


}
