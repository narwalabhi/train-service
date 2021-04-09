package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.Trip;
import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.service.TripSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.PUT;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("trip-schedules/")
public class TripsSchedulesController {

    @Value("${codes.trip-schedule-active}")
    String activeCode;

    @Value(("${codes.trip-schedule-cancelled}"))
    String cancelCode;

    @Autowired
    TripSchedulesService tripSchedulesService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/add")
    public TripSchedule createTripSchedule(@RequestBody TripSchedule tripSchedule){
        System.out.println(tripSchedule);
        tripSchedule.setStatus(activeCode);
        return tripSchedulesService.createTripSchedule(tripSchedule);
    }

    @PutMapping("/update/{tripScheduleId}")
    public TripSchedule updateTripSchedule(@PathVariable String tripScheduleId, @RequestBody TripSchedule tripSchedule){
        System.out.println(tripSchedule);
        return tripSchedulesService.updateTripSchedule(tripScheduleId, tripSchedule);
    }

    @DeleteMapping("/delete/{tripScheduleId}")
    public void deleteTripSchedule(@PathVariable String tripScheduleId){
        tripSchedulesService.deleteTripSchedule(tripScheduleId);
    }

    @GetMapping("/get/{tripScheduleId}")
    public TripSchedule getTripSchedule(@PathVariable String tripScheduleId){
        return tripSchedulesService.getTripSchedule(tripScheduleId);
    }

    @GetMapping("/get-trip-by-id/{tripId}/{date}")
    public TripSchedule getTripScheduleByTripId(@PathVariable String tripId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        TripSchedule tripSchedule = tripSchedulesService.getTripScheduleByTripIdAndDate(tripId, date);
        TripSchedule tripSchedule1 = tripSchedulesService.getTripScheduleByTripIdAndDate2(tripId, date);
        System.out.println(DateTimeFormat.ISO.TIME);
        System.out.println("tripId & Date " + date.toString() + " " + tripSchedule + " " + tripSchedule1);
        if (tripSchedule != null){
            return tripSchedule;
        }
        return null;
    }

    @GetMapping("/get-trip-by-id/{tripId}")
    public TripSchedule getTripScheduleByTripId(@PathVariable String tripId){
        return tripSchedulesService.getTripSchedule(tripId);
    }

    @PutMapping("/cancel-trip-schedule/{tripScheduleId}")
    public void cancelTripSchedule(@PathVariable String tripScheduleId){
        Optional<TripSchedule> tripSchedule = Optional.ofNullable(tripSchedulesService.getTripSchedule(tripScheduleId));
        if(tripSchedule.isPresent()){
            tripSchedule.get().setStatus(cancelCode);
//            restTemplate.exchange();
        }
    }

}
