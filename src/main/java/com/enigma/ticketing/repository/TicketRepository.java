package com.enigma.ticketing.repository;


import com.enigma.ticketing.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class TicketRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            entityManager.createNativeQuery("INSERT INTO m_ticket (concert_id , priceTIcket, status, id) VALUES (?,?,?,?)")
                    .setParameter(1, ticket.getConcert().getId())
                    .setParameter(2, ticket.getPriceTicket())
                    .setParameter(3, ticket.getStatus().name())
                    .setParameter(4, UUID.randomUUID().toString())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery("UPDATE m_ticket SET concert_id = ?, priceTIcket = ?, status = ? WHERE id = ?")
                    .setParameter(1, ticket.getConcert().getId())
                    .setParameter(2, ticket.getPriceTicket())
                    .setParameter(3, ticket.getStatus().name())
                    .setParameter(4, ticket.getId())
                    .executeUpdate();
        }
        return ticket;
    }

    public Ticket findById(String id) {
        List<Ticket> tickets = entityManager.createNativeQuery("SELECT * FROM m_ticket WHERE id = ?", Ticket.class)
                .setParameter(1,id)
                .getResultList();
        return tickets.isEmpty() ? null : tickets.get(0);
    }

    public List<Ticket> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM m_ticket", Ticket.class)
                .getResultList();
    }

    public void delete(String id) {
        entityManager.createNativeQuery("DELETE FROM m_ticket WHERE id = ?")
                .setParameter(1,id)
                .executeUpdate();
    }
}
