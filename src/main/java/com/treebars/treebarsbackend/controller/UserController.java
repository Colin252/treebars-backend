package com.treebars.treebarsbackend.controller;

import com.treebars.treebarsbackend.entity.User;
import com.treebars.treebarsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está en uso.");
        }
        User nuevoUsuario = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
