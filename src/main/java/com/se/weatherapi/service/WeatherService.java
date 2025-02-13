package com.se.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.weatherapi.dto.WeatherDto;
import com.se.weatherapi.dto.WeatherResponse;
import com.se.weatherapi.model.WeatherEntity;
import com.se.weatherapi.repository.WeatherEntityRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class WeatherService {
    @Value("${weather-stack.api-url}")
    private String apiUrl;
    private  final WeatherEntityRepository weatherEntityRepository;
    private final RestTemplate restTemplate;
    public WeatherService( WeatherEntityRepository weatherEntityRepository, RestTemplate restTemplate) {
        this.weatherEntityRepository = weatherEntityRepository;
        this.restTemplate = restTemplate;
    }
    private final ObjectMapper objectMapper = new ObjectMapper();
    @RateLimiter(name = "api-limit")
    public ResponseEntity<WeatherDto> getWeatherByCityName(String cityName) {
        Optional<WeatherEntity> weatherEntityOptional = weatherEntityRepository.findFirstByCityNameOrderByUpdateTime(cityName);
       if (weatherEntityOptional.isPresent() && weatherEntityOptional.get().getUpdateTime().isAfter(LocalDateTime.now().minusMinutes(30))) {
         return ResponseEntity.ok(new WeatherDto(weatherEntityOptional.get())) ;
       }
       return  ResponseEntity.ok(new WeatherDto(getWeatherFromWeatherStack(cityName)));
    }

    private WeatherEntity getWeatherFromWeatherStack(String cityName) {

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl+cityName, String.class);
        try {
            WeatherResponse weatherResponse = objectMapper.readValue(response.getBody(), WeatherResponse.class);
            return saveWeatherEntity(cityName, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        WeatherEntity weatherEntity = new WeatherEntity(city,
                weatherResponse.getLocation().getName(),
                weatherResponse.getLocation().getCountry(),
                weatherResponse.getCurrent().getTemperature(),
                LocalDateTime.parse(weatherResponse.getLocation().getLocalTime(),formatter)
        );

        return weatherEntityRepository.save(weatherEntity);
    }
}
