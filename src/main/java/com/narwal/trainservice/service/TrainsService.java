package com.narwal.trainservice.service;

import com.narwal.trainservice.model.Train;
import com.narwal.trainservice.repository.TrainsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainsService {

    @Autowired
    TrainsRepo trainsRepo;

    public Train createTrain(Train train){
        return trainsRepo.save(train);
    }

    public Train updateTrain(String number, Train train){
        Optional<Train> trainData = trainsRepo.findByNumber(number);
        if(trainData.isPresent()){
            trainsRepo.save(train);
        }
        return train;
    }

    public void deleteTrain(String number){
        trainsRepo.deleteByNumber(number);
    }

    public Optional<Train> getTrain(String number){
        return trainsRepo.findByNumber(number);
    }

    public List<Train> getAllTrains() {
        return trainsRepo.findAll();
    }
}
