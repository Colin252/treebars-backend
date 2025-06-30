package com.treebars.treebarsbackend.service;

import com.treebars.treebarsbackend.entity.Routine;
import com.treebars.treebarsbackend.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();
    }

    public Routine crearRutina(Routine rutina) {
        return routineRepository.save(rutina);
    }

    public Optional<Routine> findById(Long id) {
        return routineRepository.findById(id);
    }

    public Routine save(Routine rutina) {
        return routineRepository.save(rutina);
    }

    public void deleteRoutine(Long id) {
        routineRepository.deleteById(id);
    }
}
