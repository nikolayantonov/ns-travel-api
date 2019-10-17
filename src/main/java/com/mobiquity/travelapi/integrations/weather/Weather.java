package com.mobiquity.travelapi.integrations.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Weather {

    private Currently currently;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    class Currently
    {
        private String summary;
        private double temperature;
        private double humidity;
        private double windSpeed;
    }

}
