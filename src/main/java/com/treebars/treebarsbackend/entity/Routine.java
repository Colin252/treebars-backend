package com.treebars.treebarsbackend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"}) // ✅ evita recursión
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Routine() {}

    public Routine(String name, String dayOfWeek, String type, List<String> exercises) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.type = type;
        this.exercises = exercises;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDayOfWeek() { return dayOfWeek; }

    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public List<String> getExercises() { return exercises; }

    public void setExercises(List<String> exercises) { this.exercises = exercises; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
