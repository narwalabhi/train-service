package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.service.TripSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trip-schedules/")
public class TripsSchedulesController {

    @Autowired
    TripSchedulesService tripSchedulesService;

    @PostMapping("/add")
    public TripSchedule createTripSchedule(@RequestBody TripSchedule tripSchedule){
        return tripSchedulesService.createTripSchedule(tripSchedule);
    }

}
