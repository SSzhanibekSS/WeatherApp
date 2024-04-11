package joni.thirdproject.ThirdProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import joni.thirdproject.ThirdProject.models.Sensor;
import joni.thirdproject.ThirdProject.models.Weather;

public class WeatherDTO {
    @Min(value = -100, message = "humidity should be more then -100")
    @Max(value = 100, message = "humidity should be less then 100")
    private double humidity;
    @NotNull(message = "raining shouldn't be empty")
    private boolean raining;
    @NotNull(message = "sensor name shouldn't be empty")
    private Sensor sensor;
    public WeatherDTO(){}

    public WeatherDTO(double humidity, boolean raining, Sensor sensor) {
        this.humidity = humidity;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
