package com.weatherapi.weather_app.controller;

import com.weatherapi.weather_app.entity.Weatherdata;
import com.weatherapi.weather_app.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/weather-api")
public class Weatherappcontroller {
    private final WeatherService weatherservice;
    public Weatherappcontroller(WeatherService weatherservice)
    {
        this.weatherservice=weatherservice;
    }
    @PostMapping("/loadcsv")
    public String Weatherdataload(@RequestParam("file") MultipartFile file)
    {
        weatherservice.loadCSVfile(file);
        return  "CSV file loaded successfully";
    }
    @GetMapping("/print")
    public List<Weatherdata>  printDB()
    {
        return weatherservice.printWeatherData();
    }
    @GetMapping("/sort")
    public  List<Weatherdata> SortDB(@RequestParam String field, @RequestParam String type)
    {
        return weatherservice.sortWeatherData(field,type);
    }
}
