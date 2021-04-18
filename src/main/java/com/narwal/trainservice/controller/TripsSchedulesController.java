package com.narwal.trainservice.controller;

import com.narwal.trainservice.exception.ApiRequestException;
import com.narwal.trainservice.exception.EntityNotFoundException;
import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.service.TripSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@RestController
@RequestMapping("/train/trip-schedules/")
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
    public ResponseEntity<TripSchedule> createTripSchedule(@RequestBody TripSchedule tripSchedule) {
//        System.out.println(tripSchedule);
        tripSchedule.setStatus(activeCode);
        Optional<TripSchedule> tripScheduleData = tripSchedulesService.createTripSchedule(tripSchedule);
        if (tripScheduleData.isPresent()) {
            return ResponseEntity.ok(tripScheduleData.get());
        } else throw new ApiRequestException("Bad JSON");
    }

    @PutMapping("/update/{tripScheduleId}")
    public ResponseEntity<TripSchedule> updateTripSchedule(@PathVariable String tripScheduleId, @RequestBody TripSchedule tripSchedule) {
//        System.out.println(tripSchedule);
        Optional<TripSchedule> tripScheduleData = tripSchedulesService.updateTripSchedule(tripScheduleId, tripSchedule);
        if (tripScheduleData.isPresent()) {
            return ResponseEntity.ok(tripScheduleData.get());
        } else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");

    }

    @DeleteMapping("/delete/{tripScheduleId}")
    public ResponseEntity<TripSchedule> deleteTripSchedule(@PathVariable String tripScheduleId) {
        Optional<TripSchedule> tripSchedule = tripSchedulesService.deleteTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()) {
            return ResponseEntity.ok(tripSchedule.get());
        } else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
    }

    @GetMapping("/get/{tripScheduleId}")
    public ResponseEntity<TripSchedule> getTripSchedule(@PathVariable String tripScheduleId) {
        Optional<TripSchedule> tripSchedule = tripSchedulesService.getTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()){
            return ResponseEntity.ok(tripSchedule.get());
        } else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
    }

    @GetMapping("/get-trip-by-id/{tripId}/{date}")
    public ResponseEntity<TripSchedule> getTripScheduleByTripIdAndDate(@PathVariable String tripId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.NONE) LocalDate date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//        date = simpleDateFormat.parse(date.toString());
        Optional<TripSchedule> tripSchedule = tripSchedulesService.getTripScheduleByTripIdAndDate(tripId, date);
        Optional<TripSchedule> tripSchedule2 = tripSchedulesService.getTripScheduleByTripIdAndDate2(tripId, date);
        System.out.println(DateTimeFormat.ISO.TIME);
        System.out.println("tripId & Date " + date.toString() + " " + tripSchedule);
        System.out.println("tripId & Date2 " + date.toString() + " " + tripSchedule2);
        return tripSchedule.map(ResponseEntity::ok).orElse(null);
    }

    @GetMapping("/get-trip-by-id/{tripScheduleId}")
    public ResponseEntity<TripSchedule> getTripScheduleByTripScheduleId(@PathVariable String tripScheduleId) {
        Optional<TripSchedule> tripSchedule = tripSchedulesService.getTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()){
            return ResponseEntity.ok(tripSchedule.get());
        }else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
    }

    @PutMapping("/cancel-trip-schedule/{tripScheduleId}")
    public void cancelTripSchedule(@PathVariable String tripScheduleId) {
        Optional<TripSchedule> tripSchedule = tripSchedulesService.getTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()) {
            tripSchedule.get().setStatus(cancelCode);
//            restTemplate.exchange();
        }
    }

}
