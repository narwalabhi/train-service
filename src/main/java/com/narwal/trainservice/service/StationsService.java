package com.narwal.trainservice.service;

import com.narwal.trainservice.model.Station;
import com.narwal.trainservice.repository.StationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationsService {

    @Autowired
    StationsRepo stationsRepo;

    public Optional<Station> createStation(Station station){
        return Optional.of(stationsRepo.save(station));
    }

    public Optional<Station> deleteStation(String id){
        Optional<Station> station = stationsRepo.findById(id);
        if(station.isPresent()){
            stationsRepo.deleteByCode(id);
            return station;
        }
        return Optional.empty();
    }

    public Optional<Station> updateStation(String id, Station station) {
        Optional<Station> stationData = stationsRepo.findById(id);
        if(stationData.isPresent()){
            station.setId(id);
            return Optional.of(stationsRepo.save(station));
        }
        return Optional.empty();
    }

    public Optional<Station> getStationById(String id){
        return stationsRepo.findById(id);
    }

    public Optional<Station> getStationByCode(String stationCode){
        Optional<Station> station = stationsRepo.findByCode(stationCode);
        return station;
    }

    public Optional<List<Station>> getAll() {
        return Optional.ofNullable(stationsRepo.findAll());
    }

    public List<Station> search(String term){
        return stationsRepo.findByNameLike(term);
    }

}
