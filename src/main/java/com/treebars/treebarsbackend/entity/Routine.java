package com.treebars.treebarsbackend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dayOfWeek;
    private String type;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> exercises;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getExercises() {
        return exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }
}
