package com.narwal.trainservice.repository;

import com.narwal.trainservice.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripsRepo extends MongoRepository<Trip, String> {
    public Optional<Trip> findByTripId(String tripId);

    public void deleteByTripId(String tripId);

    public List<Trip> findBySourceStationCodeAndDestinationStationCode(String srcStationCode, String destStationCode);

}

