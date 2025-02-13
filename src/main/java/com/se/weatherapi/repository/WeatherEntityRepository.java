package com.se.weatherapi.repository;

import com.se.weatherapi.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherEntityRepository extends JpaRepository<WeatherEntity, Long> {
    Optional<WeatherEntity> findFirstByCityNameOrderByUpdateTime(String cityName);


}
