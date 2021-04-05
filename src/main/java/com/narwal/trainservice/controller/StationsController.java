package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.Station;
import com.narwal.trainservice.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stations")
public class StationsController {

    @Autowired
    StationsService stationsService;

    @PostMapping("/add")
    public Station createStation(@RequestBody Station station){
        return stationsService.createStation(station);
    }

}
