package com.narwal.trainservice.service;

import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.repository.TripSchedulesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class TripSchedulesService {

    @Autowired
    TripSchedulesRepo tripSchedulesRepo;

    public TripSchedule createTripSchedule(TripSchedule tripSchedule){
        return tripSchedulesRepo.save(tripSchedule);
    }

}
