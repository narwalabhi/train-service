package com.narwal.trainservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-mm-dd", iso = DateTimeFormat.ISO.DATE_TIME)
    private Date tripDate;
    private int firstAcAvailableSeats;
    private int secondAcAvailableSeats;
    private int thirdAcAvailableSeats;
    private int FirstClassAcAvailableSeats;
    private int chairCarAcAvailableSeats;
    private int SleeperAvailableSeats;
    private String tripId;
    private String status;
    @Override
    public String toString() {
        return "TripSchedule{" +
                "id='" + id + '\'' +
                ", tripDate=" + tripDate +
                ", firstAcAvailableSeats=" + firstAcAvailableSeats +
                ", secondAcAvailableSeats=" + secondAcAvailableSeats +
                ", thirdAcAvailableSeats=" + thirdAcAvailableSeats +
                ", FirstClassAcAvailableSeats=" + FirstClassAcAvailableSeats +
                ", chairCarAcAvailableSeats=" + chairCarAcAvailableSeats +
                ", SleeperAvailableSeats=" + SleeperAvailableSeats +
                ", tripId='" + tripId + '\'' +
                '}';
    }
}
