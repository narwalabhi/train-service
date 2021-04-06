package com.narwal.trainservice.service;

import com.narwal.trainservice.model.Station;
import com.narwal.trainservice.repository.StationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StationsService {

    @Autowired
    StationsRepo stationsRepo;

    public Station createStation(Station station){
        return stationsRepo.save(station);
    }

    public void deleteStation(String stationCode){
        stationsRepo.deleteByCode(stationCode);
    }

    public Station updateStation(String stationCode, Station station) {
        Optional<Station> stationData = stationsRepo.findByCode(stationCode, station);
        if(stationData.isPresent()){
            stationsRepo.save(station);
        }
        return station;
    }

    public Station getStation(String stationCode){
        return stationsRepo.findByCode(stationCode);
    }

}
