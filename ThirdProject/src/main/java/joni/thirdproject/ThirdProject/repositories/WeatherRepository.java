package joni.thirdproject.ThirdProject.repositories;

import joni.thirdproject.ThirdProject.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findByRaining(boolean b);
}
