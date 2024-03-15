package com.enigma.ticketing.service;

import com.enigma.ticketing.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
