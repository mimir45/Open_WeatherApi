package com.se.weatherapi.dto.apiRespons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Location {
    private String name;
    private String country;
    @JsonProperty("localtime")
    private String localTime;
    private String region;
}
