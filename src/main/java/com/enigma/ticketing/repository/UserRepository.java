package com.enigma.ticketing.repository;

import com.enigma.ticketing.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    public User findById(String id) {
        List<User> users = entityManager.createNativeQuery("SELECT * FROM m_user WHERE id = ?", User.class)
                .setParameter(1, id)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}
