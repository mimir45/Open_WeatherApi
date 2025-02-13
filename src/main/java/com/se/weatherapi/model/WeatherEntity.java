package com.se.weatherapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updateTime;
    private LocalDateTime responseTime;

    public WeatherEntity(String city, String name, String country, Integer temperature, LocalDateTime parse) {
        this.requestCityName = city;
          this.cityName = name;
          this.country = country;
          this.temperature = temperature;
          this.updateTime = parse;
          this.responseTime = LocalDateTime.now();
    }


}
