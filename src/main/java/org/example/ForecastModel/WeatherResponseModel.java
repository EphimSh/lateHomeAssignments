package org.example.ForecastModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherResponseModel {
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private List<DataObj> data;

    @Data
    public static class Meta{
        @JsonProperty("generated")
        private String generated;
        @JsonProperty("stations")
        private List<Integer> stations;


    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class DataObj{
        @JsonProperty("date")
        private String date;
        @JsonProperty("tavg")
        private double averageTemperature;
        @JsonProperty("tmin")
        private double minTemperature;
        @JsonProperty("tmax")
        private double maxTemperature;
        @JsonProperty("prcp")
        private int perception;
        @JsonProperty("snow")
        private String snow;
        @JsonProperty("wdir")
        private int windDirection;
        @JsonProperty("wspd")
        private double windSpeed;
        @JsonProperty("wpgt")
        private double windSpeedGust;
        @JsonProperty("pres")
        private int pressure;
        @JsonProperty("tsun")
        private String sun;

    }

}
