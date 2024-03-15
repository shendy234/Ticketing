package com.enigma.ticketing.repository;


import com.enigma.ticketing.entity.Concert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ConcertRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Concert save(Concert concert) {
        if (concert.getId() == null) {
            entityManager.createNativeQuery("INSERT INTO m_concert (concertName, concertDate, location, capacity, id) VALUES (?,?,?,?,?)")
                    .setParameter(1, concert.getConcertName())
                    .setParameter(2, concert.getConcertDate())
                    .setParameter(3, concert.getLocation())
                    .setParameter(4, concert.getCapacity())
                    .setParameter(5, UUID.randomUUID().toString())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery("UPDATE m_concert SET concertName = ?, concertDate = ?, location = ?, capacity = ? WHERE id = ?")
                    .setParameter(1, concert.getConcertName())
                    .setParameter(2, concert.getConcertDate())
                    .setParameter(3, concert.getLocation())
                    .setParameter(4, concert.getCapacity())
                    .setParameter(5, concert.getId())
                    .executeUpdate();
        }
        return concert;
    }

    public Concert findById(String id) {
        List<Concert> concerts = entityManager.createNativeQuery("SELECT * FROM m_concert WHERE id = ?", Concert.class)
                .setParameter(1, id)
                .getResultList();
        return concerts.isEmpty() ? null : concerts.get(0);
    }

    public List<Concert> findByName(String concertName) {
        return entityManager.createNativeQuery("SELECT * FROM m_concert WHERE concertName LIKE ?", Concert.class)
                .setParameter(1, "%" + concertName + "%")
                .getResultList();
    }

    public List<Concert> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM m_concert", Concert.class)
                .getResultList();
    }

    public void delete(String id) {
        entityManager.createNativeQuery("DELETE FROM m_concert WHERE id = ?")
                .setParameter(1, id)
                .getResultList();
    }
}
