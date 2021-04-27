package com.narwal.trainservice.controller;

import com.narwal.trainservice.exception.ApiRequestException;
import com.narwal.trainservice.exception.EntityNotFoundException;
import com.narwal.trainservice.model.Train;
import com.narwal.trainservice.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/train")
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

    @PostMapping("/add")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        Optional<Train> trainData = trainsService.createTrain(train);
        if (trainData.isPresent()){
            return ResponseEntity.ok(trainData.get());
        } else throw new ApiRequestException("Bad JSON.");

    }

    @PutMapping("/update/{trainId}")
    public ResponseEntity<Train> updateTrain(@PathVariable String trainId, @RequestBody Train train){
        Optional<Train> trainData = trainsService.updateTrain(trainId, train);
        System.out.println(train + " " +  trainData);
        if (trainData.isPresent()){
            return ResponseEntity.ok(trainData.get());
        }else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
    }

    @DeleteMapping("/delete/{trainId}")
    public ResponseEntity<Train> deleteTrain(@PathVariable String trainId){
        Optional<Train> trip = trainsService.deleteTrain(trainId);
        if (trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
    }

    @GetMapping("/get-by-id/{trainId}")
    public ResponseEntity<Train> getTrainById(@PathVariable String trainId){
        Optional<Train> train = trainsService.getTrainById(trainId);
        if (train.isPresent()){
            return ResponseEntity.ok(train.get());
        } else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
    }

    @GetMapping("/get/{number}")
    public ResponseEntity<Train> getTrainByNumber(@PathVariable String number){
        Optional<Train> train = trainsService.getTrainByNumber(number);
        if (train.isPresent()){
            return ResponseEntity.ok(train.get());
        } else throw new EntityNotFoundException("Train with number " + number + " was not found.");
    }

    @GetMapping("search/{fromStationCode}/{toStationCode}")
    public List<Train> searchTrains(@PathVariable String fromStationCode, @PathVariable String toStationCode){
        System.out.println(fromStationCode + " " + toStationCode);
        return trainsService.searchTrainsByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<Train>> getAllTrains(){
        Optional<List<Train>> trains = trainsService.getAll();
        if (trains.isPresent()){
            return ResponseEntity.ok(trains.get());
        }else throw new EntityNotFoundException("Trains not found.");
    }

}
