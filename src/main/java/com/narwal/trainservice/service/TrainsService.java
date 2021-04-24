package com.narwal.trainservice.service;

import com.narwal.trainservice.model.Station;
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

    public Optional<Train> createTrain(Train train){
        return Optional.of(trainsRepo.save(train));
    }

    public Optional<Train> updateTrain(String id, Train train){
        Optional<Train> trainData = trainsRepo.findById(id);
        if(trainData.isPresent()){
            train.setId(id);
            return Optional.of(trainsRepo.save(train));
        }
        return Optional.empty();
    }

    public Optional<Train> deleteTrain(String trainId){
        Optional<Train> train = trainsRepo.findById(trainId);
        if(train.isPresent()){
            trainsRepo.deleteById(trainId);
            return train;
        }
        return Optional.empty();
    }

    public Optional<Train> getTrainByNumber(String number){
        return trainsRepo.findByNumber(number);
    }

    public Optional<Train> getTrainById(String trainId){
        return trainsRepo.findById(trainId);
    }

    public List<Train> searchTrainsByFromStationCodeAndToStationCode(String fromStationCode, String toStationCode) {
        return trainsRepo.findTrainByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
    }

    public Optional<List<Train>> getAll() {
        return Optional.ofNullable(trainsRepo.findAll());
    }
}
