package com.enigma.ticketing.repository;

import com.enigma.ticketing.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Role> findByName(String name) {
        return entityManager
                .createNativeQuery("SELECT * FROM m_role WHERE name = :name", Role.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Role save(Role role) {
        if (role.getId() == null) {
            entityManager.persist(role);
            return role;
        } else {
            return entityManager.merge(role);
        }
    }
}