package com.treebars.treebarsbackend.service;

import com.treebars.treebarsbackend.entity.Routine;
import com.treebars.treebarsbackend.entity.User;
import com.treebars.treebarsbackend.repository.RoutineRepository;
import com.treebars.treebarsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UserRepository userRepository;

    public Routine crearRutinaConUsuario(Routine rutina, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));
        rutina.setUser(user);
        return routineRepository.save(rutina);
    }

    public List<Routine> getAllRoutinesByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));
        return routineRepository.findByUser(user);
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
