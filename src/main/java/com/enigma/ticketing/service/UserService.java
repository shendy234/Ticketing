package com.enigma.ticketing.service;

import com.enigma.ticketing.entity.AppUser;
import com.enigma.ticketing.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
    User getUserByUserId(String id);
}

