package com.enigma.ticketing.repository;

import com.enigma.ticketing.entity.UserCredential;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class UserCredentialRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserCredential save(UserCredential userCredential) {
        if (userCredential.getId() == null) {
            entityManager.persist(userCredential);
            return userCredential;
        } else {
            return entityManager.merge(userCredential);
        }
    }

    public Optional<UserCredential> findById(String id) {
        return Optional.ofNullable(entityManager.find(UserCredential.class, id));
    }

    public Optional<UserCredential> findByUsername(String username) {
        return entityManager
                .createNativeQuery("SELECT * FROM m_user_credential WHERE username = :username", UserCredential.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }
}