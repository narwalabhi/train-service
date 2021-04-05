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
    private String destinationStationCode;
    private String trainNo;
}
