package com.treebars.treebarsbackend.controller;

import com.treebars.treebarsbackend.entity.Routine;
import com.treebars.treebarsbackend.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/routines")
@CrossOrigin(origins = "*")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines(Principal principal) {
        String email = principal.getName();
        List<Routine> routines = routineService.getAllRoutinesByUserEmail(email);

        if (routines.isEmpty()) {
            System.out.println("⚠️ No hay rutinas para el usuario: " + email);
        } else {
            System.out.println("✅ Rutinas encontradas para " + email + ": " + routines.size());
            System.out.println("🧪 Rutinas encontradas: " + routines); // 🔍 log extra
        }

        return ResponseEntity.ok(routines);
    }

    @PostMapping
    public ResponseEntity<Routine> crearRutina(@RequestBody Routine rutina, Principal principal) {
        System.out.println("📥 Recibida nueva rutina: " + rutina.getName());
        String email = principal.getName();
        Routine nueva = routineService.crearRutinaConUsuario(rutina, email);
        return ResponseEntity.status(201).body(nueva);
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
