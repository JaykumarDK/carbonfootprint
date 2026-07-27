package com.infosys.carbonfootprint.controller;

import com.infosys.carbonfootprint.dto.LoginRequestDTO;
import com.infosys.carbonfootprint.dto.LoginResponseDTO;
import com.infosys.carbonfootprint.dto.PasswordResetDTO;
import com.infosys.carbonfootprint.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
        @Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO response =
            authService.login(loginRequestDTO);

        return ResponseEntity.ok(response);
    }
    //RestPassword Controller
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
        @Valid @RequestBody PasswordResetDTO passwordResetDTO) {

        String response =
            authService.resetPassword(passwordResetDTO);

        return ResponseEntity.ok(response);
    }
}
