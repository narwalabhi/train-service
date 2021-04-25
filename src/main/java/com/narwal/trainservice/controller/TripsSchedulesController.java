package com.narwal.trainservice.controller;

import com.narwal.trainservice.exception.ApiRequestException;
import com.narwal.trainservice.exception.EntityNotFoundException;
import com.narwal.trainservice.model.Train;
import com.narwal.trainservice.model.Trip;
import com.narwal.trainservice.model.TripSchedule;
import com.narwal.trainservice.service.TrainsService;
import com.narwal.trainservice.service.TripSchedulesService;
import com.narwal.trainservice.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
    TripsService tripsService;

    @Autowired
    TrainsService trainsService;

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
//        Optional<TripSchedule> tripSchedule2 = tripSchedulesService.getTripScheduleByTripIdAndDate2(tripId, date);
        System.out.println(DateTimeFormat.ISO.TIME);
        System.out.println("tripId & Date " + date.toString() + " " + tripSchedule);
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

    @GetMapping("/get-trip-schedules-by-date-and-stations/{fromCode}/{toCode}/{date}")
    public ResponseEntity<List<TripSchedule>> getTripSchedulesByStationsAndDate(@PathVariable String fromCode, @PathVariable String toCode, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.NONE) LocalDate date){
       List<Trip> trips = tripsService.getAlTripsBySrcAndDest(fromCode, toCode);
        System.out.println(trips);
        if (trips != null) {
            List<TripSchedule> existingTripSchedules = new ArrayList<>();
            for (Trip trip : trips){
                Optional<TripSchedule> tripSchedule = tripSchedulesService.getTripScheduleByTripIdAndDate(trip.getTripId(), date);
                if (tripSchedule.isPresent()){
                    existingTripSchedules.add(tripSchedule.get());
                }else {
                    Optional<Train> trainData = trainsService.getTrainByNumber(trip.getTrainNo());
                    if (trainData.isPresent()){
                        Train train = trainData.get();
                        TripSchedule newTripSchedule = new TripSchedule(
                                date,
                                train.getFirstAcSeats(),
                                train.getSecondAcSeats(),
                                train.getThirdAcSeats(),
                                train.getFirstClassSeats(),
                                train.getChairCarSeats(),
                                train.getSleeperSeats(),
                                trip.getTripId(),
                                activeCode);
                        Optional<TripSchedule> tripScheduleData = tripSchedulesService.createTripSchedule(newTripSchedule);
                        tripScheduleData.ifPresent(existingTripSchedules::add);
                    }
                }
            }
            return ResponseEntity.ok(existingTripSchedules);
        }
        throw new EntityNotFoundException("No trips available!");
    }

}
