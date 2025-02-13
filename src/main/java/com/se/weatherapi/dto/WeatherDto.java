package com.se.weatherapi.dto;

import com.se.weatherapi.model.WeatherEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private String cityName;
    private String countryName;
    private Integer temperature;

    public WeatherDto(WeatherEntity from){
        this.cityName = from.getCityName();
        this.countryName = from.getCountry();
        this.temperature = from.getTemperature();
    }
}
