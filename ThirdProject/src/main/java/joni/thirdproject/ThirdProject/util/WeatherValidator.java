package joni.thirdproject.ThirdProject.util;

import joni.thirdproject.ThirdProject.dto.WeatherDTO;
import joni.thirdproject.ThirdProject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class WeatherValidator implements Validator {
    private final SensorService service;
    @Autowired
    public WeatherValidator(SensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return WeatherDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WeatherDTO weather = (WeatherDTO) target;

        if(service.getSensorByName(weather.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "We don't have sensor with this name");
        }
    }
}
