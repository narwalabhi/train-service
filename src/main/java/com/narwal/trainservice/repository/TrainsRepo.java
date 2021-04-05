package com.narwal.trainservice.repository;

import com.narwal.trainservice.model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainsRepo extends MongoRepository<Train, String> {

    public Optional<Train> findByNumber(String number);

    public void deleteByNumber(String number);

    public List<Train> findTrainByFromStationCodeAndToStationCode(String fromStationCode, String toStationCode);
}
