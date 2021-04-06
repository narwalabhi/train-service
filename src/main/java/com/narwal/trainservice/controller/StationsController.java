package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.Station;
import com.narwal.trainservice.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stations")
public class StationsController {

    @Autowired
    StationsService stationsService;

    @PostMapping("/add")
    public Station createStation(@RequestBody Station station){
        return stationsService.createStation(station);
    }

    @PutMapping("/update/{stationCode}")
    public Station updateStation(@PathVariable String stationCode, @RequestBody Station station){
        return stationsService.updateStation(stationCode, station);
    }

    @DeleteMapping("/delete/{stationCode}")
    public void deleteStation(@PathVariable String stationCode){
        stationsService.deleteStation(stationCode);
    }

    @GetMapping("/get/{stationCode}")
    public Station getStation(@PathVariable String stationCode){
        return stationsService.getStation(stationCode);
    }

}
