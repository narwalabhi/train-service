package com.narwal.trainservice.service;

import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.repository.TripSchedulesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TripSchedulesService {

    @Autowired
    TripSchedulesRepo tripSchedulesRepo;

    public TripSchedule createTripSchedule(TripSchedule tripSchedule){
        return tripSchedulesRepo.save(tripSchedule);
    }

    public TripSchedule updateTripSchedule(String tripScheduleID, TripSchedule tripSchedule) {
        Optional<TripSchedule> tripScheduleData = tripSchedulesRepo.findById(tripScheduleID);
        if(tripScheduleData.isPresent()){
            System.out.println("service update " + tripSchedule);
            tripSchedulesRepo.save(tripSchedule);
        }
        return tripSchedule;
    }

    public void deleteTripSchedule(String tripId){
        tripSchedulesRepo.deleteByTripId(tripId);
    }

    public TripSchedule getTripSchedule(String tripScheduleId){
        Optional<TripSchedule> tripSchedule = tripSchedulesRepo.findById(tripScheduleId);
        return tripSchedule.orElse(null);
    }
    public TripSchedule getTripScheduleByTripId(String tripId){
        return tripSchedulesRepo.findByTripId(tripId);
    }

    public TripSchedule getTripScheduleByTripIdAndDate(String tripId, Date date){
        return tripSchedulesRepo.findByTripIdAndTripDate(tripId, date);
    }

    public TripSchedule getTripScheduleByTripIdAndDate2(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return tripSchedulesRepo.findByTripIdAndTripDate(tripId, date);
    }



}
