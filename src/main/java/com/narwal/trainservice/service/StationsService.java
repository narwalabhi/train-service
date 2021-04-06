package com.narwal.trainservice.service;

import com.narwal.trainservice.model.Station;
import com.narwal.trainservice.repository.StationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
