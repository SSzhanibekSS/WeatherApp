package joni.thirdproject.ThirdProject.errors;

import joni.thirdproject.ThirdProject.dto.WeatherDTO;

public class WeatherError extends RuntimeException{
    public WeatherError(String msg){
        super(msg);
    }
}
