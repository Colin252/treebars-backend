package com.treebars.treebarsbackend.controller;

import com.treebars.treebarsbackend.entity.Routine;
import com.treebars.treebarsbackend.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines")
@CrossOrigin(origins = "*") // Permite CORS desde cualquier origen
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines() {
        List<Routine> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @PostMapping
    public ResponseEntity<Routine> crearRutina(@RequestBody Routine rutina) {
        Routine nueva = routineService.crearRutina(rutina);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Routine> actualizarRutina(@PathVariable Long id, @RequestBody Routine rutinaActualizada) {
        return routineService.findById(id)
                .map(rutinaExistente -> {
                    rutinaExistente.setName(rutinaActualizada.getName());
                    rutinaExistente.setDayOfWeek(rutinaActualizada.getDayOfWeek());
                    rutinaExistente.setType(rutinaActualizada.getType());
                    rutinaExistente.setExercises(rutinaActualizada.getExercises());
                    Routine rutinaGuardada = routineService.save(rutinaExistente);
                    return ResponseEntity.ok(rutinaGuardada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }
}
