package joni.thirdproject.ThirdProject.util;

import jakarta.persistence.Column;
import joni.thirdproject.ThirdProject.dto.SensorDTO;
import joni.thirdproject.ThirdProject.models.Sensor;
import joni.thirdproject.ThirdProject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SensorValidator implements Validator {
    private final SensorService service;
    @Autowired
    public SensorValidator(SensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if(service.getSensorByName(sensorDTO.getName()).isPresent()){
            errors.rejectValue("name", "","Sensor with this name already exists");
        }
    }
}
