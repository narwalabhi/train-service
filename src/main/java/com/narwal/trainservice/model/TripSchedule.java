package com.narwal.trainservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Document("trip_schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripSchedule {
    @Id
    @Indexed
    private String id;
    @DateTimeFormat(pattern = "yyyy-mm-dd", iso = DateTimeFormat.ISO.NONE)
    private LocalDate tripDate;
    private int firstAcAvailableSeats;
    private int secondAcAvailableSeats;
    private int thirdAcAvailableSeats;
    private int FirstClassAcAvailableSeats;
    private int chairCarAcAvailableSeats;
    private int SleeperAvailableSeats;
    private String tripId;
    private String status;


    public TripSchedule(LocalDate tripDate, int firstAcAvailableSeats, int secondAcAvailableSeats, int thirdAcAvailableSeats, int firstClassAcAvailableSeats, int chairCarAcAvailableSeats, int sleeperAvailableSeats, String tripId, String status) {
        this.tripDate = tripDate;
        this.firstAcAvailableSeats = firstAcAvailableSeats;
        this.secondAcAvailableSeats = secondAcAvailableSeats;
        this.thirdAcAvailableSeats = thirdAcAvailableSeats;
        FirstClassAcAvailableSeats = firstClassAcAvailableSeats;
        this.chairCarAcAvailableSeats = chairCarAcAvailableSeats;
        SleeperAvailableSeats = sleeperAvailableSeats;
        this.tripId = tripId;
        this.status = status;
    }
}
