package com.se.weatherapi.controller;

import com.se.weatherapi.dto.WeatherDto;
import com.se.weatherapi.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    @RateLimiter(name = "api-limit")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable String city) {
        return weatherService.getWeatherByCityName(city);
    }
}
