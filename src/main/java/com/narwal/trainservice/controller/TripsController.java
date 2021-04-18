package com.narwal.trainservice.controller;

import com.narwal.trainservice.exception.ApiRequestException;
import com.narwal.trainservice.exception.EntityNotFoundException;
import com.narwal.trainservice.model.Train;
import com.narwal.trainservice.model.Trip;
import com.narwal.trainservice.model.TripSearchRequestBody;
import com.narwal.trainservice.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/train/trips")
public class TripsController {

    @Autowired
    TripsService tripsService;

    @PostMapping("/add")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip){
        Optional<Trip> tripData = tripsService.createTrip(trip);
        if (tripData.isPresent()) {
            return ResponseEntity.ok(tripData.get());
        } else throw new ApiRequestException("Bad Json");
    }

    @PutMapping("/update/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable String tripId, @RequestBody Trip trip){
        Optional<Trip> tripData = tripsService.updateTrip(tripId, trip);
        if (tripData.isPresent()){
            return ResponseEntity.ok(tripData.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @DeleteMapping("/delete/{tripId}")
    public ResponseEntity<Trip> deleteTrip(@PathVariable String tripId){
        Optional<Trip> trip = tripsService.deleteTrip(tripId);
        if (trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @GetMapping("/get/{tripId}")
    public ResponseEntity<Trip> getTrip(@PathVariable String tripId){
        System.out.println(tripId);
        Optional<Trip> trip = tripsService.getTrip(tripId);
        if(trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    //TODO change Return type
    @GetMapping("/get-trip-by-src-dest")
    public List<Trip> getTripsBySrcAndDest(@RequestBody TripSearchRequestBody tripSearchRequestBody){
        return tripsService.getAlTripsBySrcAndDest(tripSearchRequestBody.getSrcStationCode(), tripSearchRequestBody.getDestStationCode());
    }


    //TODO change Return type
    @GetMapping("/get")
    public List<Trip> getAllTrips(){
        return tripsService.getAllTrips();
    }


}
