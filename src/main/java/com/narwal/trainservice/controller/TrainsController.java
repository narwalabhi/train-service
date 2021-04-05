package com.narwal.trainservice.controller;

import com.narwal.trainservice.model.Train;
import com.narwal.trainservice.service.TrainsService;
import com.narwal.trainservice.service.TripSchedulesService;
import com.narwal.trainservice.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trains")
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private TripsService tripsService;

    @Autowired
    private TripSchedulesService tripSchedulesService;

    @PostMapping("/add")
    public Train addTrain(@RequestBody Train train){
        return trainsService.createTrain(train);
    }

    @PutMapping("/update/{trainId}")
    public Train updateTrain(@PathVariable String trainId, @RequestBody Train train){
        return trainsService.updateTrain(trainId, train);
    }

    @DeleteMapping("/delete/{trainId}")
    public void deleteTrain(@PathVariable String trainId){
        trainsService.deleteTrain(trainId);
    }

    @GetMapping("/get/{trainId}")
    public Train getTrain(@PathVariable String trainId){
        Optional<Train> train = trainsService.getTrain(trainId);
        return train.orElseGet(Train::new);
    }

    @GetMapping("search/{fromStationCode}/{toStationCode}")
    public List<Train> searchTrains(@PathVariable String fromStationCode, @PathVariable String toStationCode){
        System.out.println(fromStationCode + " " + toStationCode);
        return trainsService.searchTrainsByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
    }

}
