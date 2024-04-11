package joni.thirdproject.ThirdProject.services;

import joni.thirdproject.ThirdProject.models.Sensor;
import joni.thirdproject.ThirdProject.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository repository;
    @Autowired
    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public Optional<Sensor> getSensorByName(String name){
        return repository.findByName(name);
    }
    @Transactional
    public void save(Sensor sensor){
        sensor.setCreatedAt(LocalDateTime.now());
        repository.save(sensor);
    }
}
