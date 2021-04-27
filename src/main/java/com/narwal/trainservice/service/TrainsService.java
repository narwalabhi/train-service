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
        Optional<Train> trainOptional = trainsRepo.findById(id);
        if(trainOptional.isPresent()){
            Train trainData = trainOptional.get();
            if(train.getName() != null){
                trainData.setName(train.getName());
            }
            if(train.getFromStationCode() != null){
                trainData.setFromStationCode(train.getFromStationCode());
            }
            if(train.getToStationCode() != null){
                trainData.setToStationCode(train.getToStationCode());
            }
            if(train.getNumber() != null){
                trainData.setNumber(train.getNumber());
            }
            if(train.getDeparture() != null){
                trainData.setDeparture(train.getDeparture());
            }
            if (train.getArrival() != null){
                trainData.setArrival(train.getArrival());
            }
            if(train.getFirstAcSeats() > 0){
                trainData.setFirstAcSeats(train.getFirstAcSeats());
            }
            if(train.getSecondAcSeats() > 0){
                trainData.setSecondAcSeats(train.getSecondAcSeats());
            }
            if(train.getThirdAcSeats() > 0){
                trainData.setThirdAcSeats(train.getThirdAcSeats());
            }
            if(train.getFirstClassSeats() > 0){
                trainData.setFirstClassSeats(train.getFirstClassSeats());
            }
            if(train.getChairCarSeats() > 0){
                trainData.setChairCarSeats(train.getChairCarSeats());
            }
            if(train.getSleeperSeats() > 0){
                trainData.setSleeperSeats(train.getSleeperSeats());
            }
            if (train.getDistance() > 0){
                trainData.setDistance(train.getDistance());
            }
            if(train.getDurationHrs() > 0){
                trainData.setDurationHrs(train.getDurationHrs());
            }
            if(train.getDurationMns() > 0){
                trainData.setDurationMns(train.getDurationMns());
            }
            return Optional.of(trainsRepo.save(trainData));
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
