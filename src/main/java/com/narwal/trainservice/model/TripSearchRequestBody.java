package com.narwal.trainservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripSearchRequestBody {
    private String srcStationCode;
    private String destStationCode;
}
