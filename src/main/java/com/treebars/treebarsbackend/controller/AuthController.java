package com.treebars.treebarsbackend.controller;

import com.treebars.treebarsbackend.dto.LoginRequest;
import com.treebars.treebarsbackend.dto.LoginResponse;
import com.treebars.treebarsbackend.dto.SignupRequest;
import com.treebars.treebarsbackend.entity.User;
import com.treebars.treebarsbackend.jwt.JwtUtils;
import com.treebars.treebarsbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000", "https://treebars-frontend.onrender.com"})
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    // 🔐 Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado ❌"));
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(403).body(Map.of("error", "Contraseña incorrecta ❌"));
        }

        String token = jwtUtils.generateToken(user.getEmail(), user.getRole(), user.getName());
        LoginResponse response = new LoginResponse(token, user.getName(), user.getRole());
        return ResponseEntity.ok(response);
    }

    // 🆕 Registro
    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:3000", "https://treebars-frontend.onrender.com"})
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        System.out.println("📥 Recibido registro: " + signupRequest.getEmail());

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(400).body(Map.of("error", "❌ Ya existe un usuario con ese email."));
        }

        User newUser = User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .role(signupRequest.getRole())
                .build();

        userRepository.save(newUser);
        return ResponseEntity.ok(Map.of("message", "✅ Usuario registrado exitosamente."));
    }
}
