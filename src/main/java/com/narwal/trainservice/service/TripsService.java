package com.narwal.trainservice.service;

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

    public Trip createTrip(Trip trip){
        return tripsRepo.save(trip);
    }

    public Trip updateTrip(String tripId, Trip trip){
        Optional<Trip> tripData = tripsRepo.findByTripId(tripId);
        if(tripData.isPresent()){
            return tripsRepo.save(trip);
        }
        return trip;
    }

    public void deleteTrip(String tripId){
        tripsRepo.deleteByTripId(tripId);
    }

    public Optional<Trip> getTrip(String tripId){
        return tripsRepo.findByTripId(tripId);
    }

    public List<Trip> getAllTrips(){
        return tripsRepo.findAll();
    }
}
