package com.weatherapi.weather_app.entity;
//datetime_utc, _conds, _dewptm, _fog, _hail, _heatindexm, _hum, _precipm, _pressurem, _rain, _snow, _tempm, _thunder, _tornado, _vism, _wdird, _wdire, _wgustm, _windchillm, _wspdm

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="weather_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weatherdata {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datetimeUtc;
    private String conds;
    private Double dewptm;
    private Integer fog;
    private Integer hail;
    private Double heatindexm;
    private Integer hum;
    private Double precipm;
    private Double pressurem;
    private Integer rain;
    private Integer snow;
    private Double tempm;
    private Integer thunder;
    private Integer tornado;
    private Double vism;
    private Integer wdird;
    private String wdire;
    private Double wgustm;
    private Double windchillm;
    private Double wspdm;
}
