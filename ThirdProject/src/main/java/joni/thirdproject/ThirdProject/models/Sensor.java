package joni.thirdproject.ThirdProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 3, max = 30, message = "Name of sensor should be between 3 and 30 characters")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "sensor")
    private List<Weather> weathers;

    public Sensor(){}

    public Sensor(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

}
