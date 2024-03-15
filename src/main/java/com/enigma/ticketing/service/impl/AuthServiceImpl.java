package com.enigma.ticketing.service.impl;

import com.enigma.ticketing.constant.ERole;
import com.enigma.ticketing.dto.request.LoginRequest;
import com.enigma.ticketing.dto.request.RegisterRequest;
import com.enigma.ticketing.dto.response.LoginResponse;
import com.enigma.ticketing.dto.response.RegisterResponse;
import com.enigma.ticketing.entity.AppUser;
import com.enigma.ticketing.entity.Role;
import com.enigma.ticketing.entity.User;
import com.enigma.ticketing.entity.UserCredential;
import com.enigma.ticketing.repository.UserCredentialRepository;
import com.enigma.ticketing.repository.UserRepository;
import com.enigma.ticketing.security.JwtUtil;
import com.enigma.ticketing.service.AuthService;
import com.enigma.ticketing.service.RoleService;
import com.enigma.ticketing.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;

    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(RegisterRequest registerRequest, ERole userRole) {
        try {
            validationUtil.validate(registerRequest);
            Role role = Role.builder()
                    .name(userRole)
                    .build();
            Role savedRole = roleService.getOrSave(role);

            UserCredential userCredential = UserCredential.builder()
                    .username(registerRequest.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .role(savedRole)
                    .build();
            UserCredential savedUserCredential = userCredentialRepository.save(userCredential);

            User user = User.builder()
                    .name(registerRequest.getName())
                    .address(registerRequest.getAddress())
                    .phoneNumber(registerRequest.getPhoneNumber())
                    .userCredentialId(savedUserCredential)
                    .build();
            userRepository.save(user);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validationUtil.validate(loginRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername().toLowerCase(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
