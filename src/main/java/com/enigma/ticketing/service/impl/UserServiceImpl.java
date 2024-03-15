package com.enigma.ticketing.service.impl;

import com.enigma.ticketing.entity.AppUser;
import com.enigma.ticketing.entity.User;
import com.enigma.ticketing.entity.UserCredential;
import com.enigma.ticketing.repository.UserCredentialRepository;
import com.enigma.ticketing.repository.UserRepository;
import com.enigma.ticketing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;
    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return new AppUser(
                userCredential.getId(),
                userCredential.getUsername(),
                userCredential.getPassword(),
                userCredential.getRole().getName());
    }

    @Override
    public User getUserByUserId(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return new AppUser(
                userCredential.getId(),
                userCredential.getUsername(),
                userCredential.getPassword(),
                userCredential.getRole().getName());
    }
}
