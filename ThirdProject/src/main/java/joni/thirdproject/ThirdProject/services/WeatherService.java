package joni.thirdproject.ThirdProject.services;

import joni.thirdproject.ThirdProject.models.Sensor;
import joni.thirdproject.ThirdProject.models.Weather;
import joni.thirdproject.ThirdProject.repositories.SensorRepository;
import joni.thirdproject.ThirdProject.repositories.WeatherRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WeatherService {
    private final WeatherRepository repository;
    private final SensorRepository sensorRepository;
    @Autowired
    public WeatherService(WeatherRepository repository, SensorRepository sensorRepository) {
        this.repository = repository;
        this.sensorRepository = sensorRepository;
    }
    public List<Weather> getAll(){
        return repository.findAll();
    }
    public List<Weather> fingRainingDay(){
        return repository.findByRaining(true);
    }
    @Transactional
    public void saveWeather(Weather weather){
        weather.setCreatedAt(LocalDateTime.now());
        Sensor sensor =  sensorRepository.findByName(weather.getSensor().getName()).get();
        sensor.setWeathers(new ArrayList<>(List.of(weather)));
        weather.setSensor(sensor);
        repository.save(weather);
    }

}
