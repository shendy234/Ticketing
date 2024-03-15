package com.enigma.ticketing.repository;


import com.enigma.ticketing.entity.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class BookingRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            entityManager.createNativeQuery("INSERT INTO m_booking (customer_id, ticket_id , numberOfTicket, totalPrice, orderDate, id) VALUES (?,?,?,?,?,?)")
                    .setParameter(1, booking.getCustomer().getId())
                    .setParameter(2, booking.getTicket().getId())
                    .setParameter(3, booking.getNumberOfTickets())
                    .setParameter(4, booking.getTotalPrice())
                    .setParameter(5, booking.getOrderDate())
                    .setParameter(6, UUID.randomUUID().toString())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery("UPDATE m_booking SET customer_id = ?, ticket_id = ?, numberOfTicket = ?, totalPrice = ?, orderDate = ? WHERE id = ?")
                    .setParameter(1, booking.getCustomer().getId())
                    .setParameter(2, booking.getTicket().getId())
                    .setParameter(3, booking.getNumberOfTickets())
                    .setParameter(4, booking.getTotalPrice())
                    .setParameter(5, booking.getOrderDate())
                    .setParameter(6, booking.getId())
                    .executeUpdate();
        }
        return booking;
    }

    public Booking findById(String id) {
        List<Booking> bookings = entityManager.createNativeQuery("SELECT * FROM m_booking WHERE id = ?", Booking.class)
                .setParameter(1,id)
                .getResultList();
        return bookings.isEmpty() ? null : bookings.get(0);
    }

    public List<Booking> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM m_booking", Booking.class)
                .getResultList();
    }

}
