package com.enigma.ticketing.service;

import com.enigma.ticketing.constant.ERole;
import com.enigma.ticketing.dto.request.LoginRequest;
import com.enigma.ticketing.dto.request.RegisterRequest;
import com.enigma.ticketing.dto.response.LoginResponse;
import com.enigma.ticketing.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest authRequest, ERole userRole);
    LoginResponse login(LoginRequest loginRequest);
}

