package com.se.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.se.weatherapi.dto.apiRespons.Current;
import com.se.weatherapi.dto.apiRespons.Location;
import com.se.weatherapi.dto.apiRespons.Request;
import lombok.Getter;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class WeatherResponse {
    private Request request;
    private Current current;
    private Location location;
}
