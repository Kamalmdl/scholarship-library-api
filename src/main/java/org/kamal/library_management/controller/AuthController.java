package org.kamal.library_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.response.AuthResponseDto;
import org.kamal.library_management.dto.request.LoginRequestDto;
import org.kamal.library_management.dto.request.RegisterRequestDto;
import org.kamal.library_management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        AuthResponseDto response = authService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        AuthResponseDto response = authService.login(requestDto);
        return ResponseEntity.ok(response);
    }
}