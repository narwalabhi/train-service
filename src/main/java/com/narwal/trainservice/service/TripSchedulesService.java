package com.narwal.trainservice.service;

import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.repository.TripSchedulesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripSchedulesService {

    @Autowired
    TripSchedulesRepo tripSchedulesRepo;

    public TripSchedule createTripSchedule(TripSchedule tripSchedule){
        return tripSchedulesRepo.save(tripSchedule);
    }

    public TripSchedule updateTripSchedule(String tripId, TripSchedule tripSchedule) {
        Optional<TripSchedule> tripScheduleData = tripSchedulesRepo.findById(tripId);
        if(tripScheduleData.isPresent()){
            tripSchedulesRepo.save(tripSchedule);
        }
        return tripSchedule;
    }

    public void deleteTripSchedule(String tripId){
        tripSchedulesRepo.deleteByTripId(tripId);
    }

    public TripSchedule getTripSchedule(String tripId){
        return tripSchedulesRepo.findByTripId(tripId);
    }

}
