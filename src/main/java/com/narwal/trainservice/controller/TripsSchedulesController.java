package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.service.TripSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip-schedules/")
public class TripsSchedulesController {

    @Autowired
    TripSchedulesService tripSchedulesService;

    @PostMapping("/add")
    public TripSchedule createTripSchedule(@RequestBody TripSchedule tripSchedule){
        return tripSchedulesService.createTripSchedule(tripSchedule);
    }

    @PutMapping("/update/{tripId}")
    public TripSchedule updateTripSchedule(@PathVariable String tripId, @RequestBody TripSchedule tripSchedule){
        return tripSchedulesService.updateTripSchedule(tripId, tripSchedule);
    }

    @DeleteMapping("/delete/{tripScheduleId}")
    public void deleteTripSchedule(@PathVariable String tripScheduleId){
        tripSchedulesService.deleteTripSchedule(tripScheduleId);
    }

    @GetMapping("/get/{tripScheduleId}")
    public TripSchedule getTripSchedule(@PathVariable String tripScheduleId){
        return tripSchedulesService.getTripSchedule(tripScheduleId);
    }

    @GetMapping("/get-trip-by-id/{tripId}")
    public TripSchedule getTripScheduleByTripId(@PathVariable String tripId){
        return tripSchedulesService.getTripScheduleByTripId(tripId);
    }

}
