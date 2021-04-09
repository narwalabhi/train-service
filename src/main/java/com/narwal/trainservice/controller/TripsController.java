package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.Trip;
import com.narwal.trainservice.model.TripSearchRequestBody;
import com.narwal.trainservice.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripsController {

    @Autowired
    TripsService tripsService;

    @PostMapping("/add")
    public Trip addTrip(@RequestBody Trip trip){
        return tripsService.createTrip(trip);
    }

    @PutMapping("/update/{tripId}")
    public Trip updateTrip(@PathVariable String tripId, @RequestBody Trip trip){
        return tripsService.updateTrip(tripId, trip);
    }

    @DeleteMapping("/delete/{tripId}")
    public void deleteTrip(@PathVariable String tripId){
        tripsService.deleteTrip(tripId);
    }

    @GetMapping("/get/{tripId}")
    public Optional<Trip> getTrip(@PathVariable String tripId){
        System.out.println(tripId);
        Optional<Trip> trip = tripsService.getTrip(tripId);
        if(trip.isPresent()){
            return tripsService.getTrip(tripId);
        }
        return Optional.empty();
    }

    @GetMapping("/get-trip-by-src-dest")
    public List<Trip> getTripsBySrcAndDest(@RequestBody TripSearchRequestBody tripSearchRequestBody){
        return tripsService.getAlTripsBySrcAndDest(tripSearchRequestBody.getSrcStationCode(), tripSearchRequestBody.getDestStationCode());
    }

    @GetMapping("/get")
    public List<Trip> getAllTrips(){
        return tripsService.getAllTrips();
    }


}
