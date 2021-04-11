package com.narwal.trainservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("trips")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {
    @Id
    @Indexed
    private String tripId;
    private int baseFare;
    private int durationHrs;
    private int durationMns;
    private String sourceStationCode;

    @Override
    public String toString() {
        return "Trip{" +
                "tripId='" + tripId + '\'' +
                ", baseFare=" + baseFare +
                ", durationHrs=" + durationHrs +
                ", durationMns=" + durationMns +
                ", sourceStationCode='" + sourceStationCode + '\'' +
                ", destinationStationCode='" + destinationStationCode + '\'' +
                ", trainNo='" + trainNo + '\'' +
                '}';
    }

    private String destinationStationCode;
    private String trainNo;
}
