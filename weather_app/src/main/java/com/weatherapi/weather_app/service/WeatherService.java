package com.weatherapi.weather_app.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.weatherapi.weather_app.entity.Weatherdata;
import com.weatherapi.weather_app.entity.Weatherdata;
import com.weatherapi.weather_app.repository.WeatherRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepo weatherRepo;

    public WeatherService(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }
    public List<Weatherdata> printWeatherData()
    {
        return weatherRepo.findAll();
    }
    public List<Weatherdata> sortWeatherData(String field,String type)
    {
        Sort data;
        if(type.equals("dec"))  data=Sort.by(field).descending();
        else  data=Sort.by(field).ascending();
        return weatherRepo.findAll(data);
    }
    public void loadCSVfile(MultipartFile file) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {

            String[] line;

            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {

                // Safety check
                if (line.length < 20) {
                    continue;
                }

                Weatherdata weatherData=new Weatherdata();

                weatherData.setDatetimeUtc(LocalDateTime.parse(line[0], dtf));
                weatherData.setConds(line[1]);

                weatherData.setDewptm(safeParseDouble(line[2]));
                weatherData.setFog(safeParseInt(line[3]));
                weatherData.setHail(safeParseInt(line[4]));
                weatherData.setHeatindexm(safeParseDouble(line[5]));
                weatherData.setHum(safeParseInt(line[6]));
                weatherData.setPrecipm(safeParseDouble(line[7]));
                weatherData.setPressurem(safeParseDouble(line[8]));
                weatherData.setRain(safeParseInt(line[9]));
                weatherData.setSnow(safeParseInt(line[10]));
                weatherData.setTempm(safeParseDouble(line[11]));
                weatherData.setThunder(safeParseInt(line[12]));
                weatherData.setTornado(safeParseInt(line[13]));
                weatherData.setVism(safeParseDouble(line[14]));
                weatherData.setWdird(safeParseInt(line[15]));
                weatherData.setWdire(line[16]);
                weatherData.setWgustm(safeParseDouble(line[17]));
                weatherData.setWindchillm(safeParseDouble(line[18]));
                weatherData.setWspdm(safeParseDouble(line[19]));

                weatherRepo.save(weatherData);
            }

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Error while processing CSV file", e);
        }
    }

    private Double safeParseDouble(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty() || isMissingValue(trimmed)) {
            return null;
        }
        try {
            return Double.parseDouble(trimmed);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Integer safeParseInt(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty() || isMissingValue(trimmed)) {
            return null;
        }
        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private boolean isMissingValue(String value) {
        return value.equalsIgnoreCase("N/A")
                || value.equalsIgnoreCase("NA")
                || value.equalsIgnoreCase("NULL")
                || value.equals("-");
    }
}
