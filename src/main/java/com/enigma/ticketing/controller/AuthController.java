package com.enigma.ticketing.controller;

import com.enigma.ticketing.constant.ERole;
import com.enigma.ticketing.dto.request.LoginRequest;
import com.enigma.ticketing.dto.request.RegisterRequest;
import com.enigma.ticketing.dto.response.CommonResponse;
import com.enigma.ticketing.dto.response.LoginResponse;
import com.enigma.ticketing.dto.response.RegisterResponse;
import com.enigma.ticketing.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest registerRequest) {
        var registerResponse = authService.register(registerRequest, ERole.ROLE_CUSTOMER);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Register Success")
                        .data(registerResponse)
                        .build());
    }
    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        var registerResponse = authService.register(registerRequest, ERole.ROLE_ADMIN);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Register Success")
                        .data(registerResponse)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Login Success")
                        .data(loginResponse)
                        .build());

    }

}