package com.narwal.trainservice.service;

import com.narwal.trainservice.model.Train;
import com.narwal.trainservice.model.Trip;
import com.narwal.trainservice.repository.TripsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripsService {

    @Autowired
    TripsRepo tripsRepo;

    public Optional<Trip> createTrip(Trip trip){
        return Optional.of(tripsRepo.save(trip));
    }

    public Optional<Trip> updateTrip(String tripId, Trip trip){
        Optional<Trip> tripData = tripsRepo.findByTripId(tripId);
        if(tripData.isPresent()){
            trip.setTripId(tripId);
            return Optional.of(tripsRepo.save(trip));
        }
        return Optional.empty();
    }

    public Optional<Trip> deleteTrip(String tripId){
        Optional<Trip> trip = tripsRepo.findById(tripId);
        if(trip.isPresent()){
            tripsRepo.deleteByTripId(tripId);
            return trip;
        }
        return Optional.empty();
    }

    public Optional<Trip> getTrip(String tripId){
        return tripsRepo.findByTripId(tripId);
    }

    public List<Trip> getAllTrips(){
        return tripsRepo.findAll();
    }

    public List<Trip> getAlTripsBySrcAndDest(String srcStationCode, String destStationCode) {
        return tripsRepo.findBySourceStationCodeAndDestinationStationCode(srcStationCode, destStationCode);
    }

}
