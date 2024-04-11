package joni.thirdproject.ThirdProject.controllers;

import jakarta.validation.Valid;
import joni.thirdproject.ThirdProject.dto.WeatherDTO;
import joni.thirdproject.ThirdProject.errors.SensorError;
import joni.thirdproject.ThirdProject.errors.WeatherError;
import joni.thirdproject.ThirdProject.models.Weather;
import joni.thirdproject.ThirdProject.services.WeatherService;
import joni.thirdproject.ThirdProject.util.WeatherValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class WeatherController {

    private final WeatherValidator validator;
    private final ModelMapper modelMapper;
    private final WeatherService service;
    @Autowired
    public WeatherController(WeatherValidator validator, ModelMapper mapper, WeatherService service) {
        this.validator = validator;
        this.modelMapper = mapper;
        this.service = service;
    }
    @GetMapping()
    public List<Weather> getAll(){
        return service.getAll();
    }
    @GetMapping("/rainyDaysCount")
    public List<WeatherDTO> getRainingDays(){
        return service.fingRainingDay().stream()
                .map(val -> modelMapper.map(val, WeatherDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody @Valid WeatherDTO weatherDTO,
                                          BindingResult result){


        validator.validate(weatherDTO, result);
        if(result.hasErrors()){
            StringBuilder sb = new StringBuilder();

            for(FieldError error : result.getFieldErrors()){
                sb.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new WeatherError(sb.toString());
        }

        service.saveWeather(modelMapper.map(weatherDTO,Weather.class));

        return new ResponseEntity<>("weather saved sucsessful!",HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorError> weatherError(WeatherError e){
        SensorError sensorError = new SensorError(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(sensorError,HttpStatus.BAD_REQUEST);
    }
    private Weather converToWeather(WeatherDTO weatherDTO){
        return modelMapper.map(weatherDTO, Weather.class);
    }

}
