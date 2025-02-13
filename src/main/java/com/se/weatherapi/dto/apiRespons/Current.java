package com.se.weatherapi.dto.apiRespons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private String observationTime;
    private Integer temperature;
}
