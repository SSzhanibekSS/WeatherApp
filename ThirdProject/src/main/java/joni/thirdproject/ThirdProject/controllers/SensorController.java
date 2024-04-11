package joni.thirdproject.ThirdProject.controllers;

import jakarta.validation.Valid;
import joni.thirdproject.ThirdProject.dto.SensorDTO;
import joni.thirdproject.ThirdProject.errors.SensorError;
import joni.thirdproject.ThirdProject.errors.SensorExistError;
import joni.thirdproject.ThirdProject.models.Sensor;
import joni.thirdproject.ThirdProject.services.SensorService;
import joni.thirdproject.ThirdProject.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService service;
    private final SensorValidator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService service, SensorValidator validator, ModelMapper modelMapper) {
        this.service = service;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensor,
                                               BindingResult bindingResult){
        System.out.println("some text");
        validator.validate(sensor,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            for(FieldError error : bindingResult.getFieldErrors()){
                sb.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorExistError(sb.toString());
        }
        service.save(modelMapper.map(sensor, Sensor.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    public ResponseEntity<SensorError> sensorExistError(SensorExistError error){
            SensorError response = new SensorError(
                    error.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
