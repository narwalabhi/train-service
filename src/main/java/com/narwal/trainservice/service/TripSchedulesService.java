package com.narwal.trainservice.service;

import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.repository.TripSchedulesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class TripSchedulesService {

    @Autowired
    TripSchedulesRepo tripSchedulesRepo;

    public Optional<TripSchedule> createTripSchedule(TripSchedule tripSchedule){
        return Optional.of(tripSchedulesRepo.save(tripSchedule));
    }

    public Optional<TripSchedule> updateTripSchedule(String tripScheduleID, TripSchedule tripSchedule) {
        Optional<TripSchedule> tripScheduleData = tripSchedulesRepo.findById(tripScheduleID);
        if(tripScheduleData.isPresent()){
            System.out.println("service update " + tripSchedule);
            tripSchedule.setId(tripScheduleID);
            return Optional.of(tripSchedulesRepo.save(tripSchedule));
        }
        return Optional.empty();
    }

    public Optional<TripSchedule> deleteTripSchedule(String tripScheduleId){
        Optional<TripSchedule> tripSchedule = tripSchedulesRepo.findById(tripScheduleId);
        if (tripSchedule.isPresent()){
            tripSchedulesRepo.deleteByTripId(tripScheduleId);
            return tripSchedule;
        }
        return Optional.empty();
    }

    public Optional<TripSchedule> getTripSchedule(String tripScheduleId){
        return tripSchedulesRepo.findById(tripScheduleId);
    }
    public TripSchedule getTripScheduleByTripId(String tripId){
        return tripSchedulesRepo.findByTripId(tripId);
    }

    public Optional<TripSchedule> getTripScheduleByTripIdAndDate(String tripId, LocalDate date){
        TripSchedule byTripIdAndTripDate = tripSchedulesRepo.findByTripIdAndTripDate(tripId, date);
        System.out.println("By" + byTripIdAndTripDate);
        return Optional.ofNullable(byTripIdAndTripDate);
    }

    public Optional<TripSchedule> getTripScheduleByTripIdAndDate2(String tripId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        TripSchedule tripScheduleByTripIdAndTripDate = tripSchedulesRepo.findTripScheduleByTripIdAndTripDate(tripId, date);
        System.out.println("by2" + tripScheduleByTripIdAndTripDate);
        return Optional.ofNullable(tripScheduleByTripIdAndTripDate);
    }



}
