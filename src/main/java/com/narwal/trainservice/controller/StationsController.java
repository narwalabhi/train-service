package com.narwal.trainservice.controller;

import com.narwal.trainservice.exception.ApiRequestException;
import com.narwal.trainservice.exception.EntityNotFoundException;
import com.narwal.trainservice.model.Station;
import com.narwal.trainservice.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/train/stations")
public class StationsController {

    @Autowired
    StationsService stationsService;

    @PostMapping("/add")
    public ResponseEntity<Station> createStation(@RequestBody Station station){
        Optional<Station> stationData = stationsService.createStation(station);
        if (stationData.isPresent()){
            return ResponseEntity.ok(stationData.get());
        }else throw new ApiRequestException("Bad JSON.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable String id, @RequestBody Station station){
        Optional<Station> stationData = stationsService.updateStation(id, station);
        if (stationData.isPresent()){
            return ResponseEntity.ok(stationData.get());
        }else throw new EntityNotFoundException("Station with id " + id + " was not found.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Station> deleteStation(@PathVariable String id){
        Optional<Station> station = stationsService.deleteStation(id);
        if (station.isPresent()){
            return ResponseEntity.ok(station.get());
        }else throw new EntityNotFoundException("Station with id " + id + " was not found.");
    }

    @GetMapping("/get/{stationCode}")
    public ResponseEntity<Station> getStation(@PathVariable String stationCode){
        Optional<Station> station = stationsService.getStationByCode(stationCode);
        if (station.isPresent()){
            return ResponseEntity.ok(station.get());
        }else throw new EntityNotFoundException("Station with stationCode " + stationCode + " was not found.");
    }

}
