package com.weatherapi.weather_app.repository;

import com.weatherapi.weather_app.entity.Weatherdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<Weatherdata,Long> {
}
