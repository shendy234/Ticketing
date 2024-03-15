package com.enigma.ticketing.service.impl;

import com.enigma.ticketing.entity.Role;
import com.enigma.ticketing.repository.RoleRepository;
import com.enigma.ticketing.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName().name());
        return optionalRole.orElseGet(() -> roleRepository.save(role));
    }
}