package com.narwal.trainservice.repository;

import com.narwal.trainservice.model.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationsRepo extends MongoRepository<Station, String> {

    public void deleteByCode(String stationCode);

    public Optional<Station> findByCode(String stationCode);
}
