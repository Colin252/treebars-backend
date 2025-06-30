package com.treebars.treebarsbackend.controller;

import com.treebars.treebarsbackend.dto.LoginResponse;
import com.treebars.treebarsbackend.entity.User;
import com.treebars.treebarsbackend.repository.UserRepository;
import com.treebars.treebarsbackend.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Header inválido");
        }
        String token = authHeader.substring(7);

        // 1️⃣ Validar token
        if (!jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.badRequest().body("Token inválido");
        }

        // 2️⃣ Extraer email y recargar usuario
        String email = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(403).body("Usuario no encontrado");
        }

        // 3️⃣ Generar nuevo JWT
        String newToken = jwtUtils.generateToken(
                user.getEmail(),
                user.getRole(),
                user.getName()
        );

        // 4️⃣ Devolver nuevo token + name + role
        return ResponseEntity.ok(
                new LoginResponse(newToken, user.getName(), user.getRole())
        );
    }
}
