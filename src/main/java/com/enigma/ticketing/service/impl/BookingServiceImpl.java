package com.enigma.ticketing.service.impl;


import com.enigma.ticketing.dto.request.BookingRequest;
import com.enigma.ticketing.entity.Booking;
import com.enigma.ticketing.entity.Ticket;
import com.enigma.ticketing.entity.User;
import com.enigma.ticketing.repository.BookingRepository;
import com.enigma.ticketing.service.BookingService;
import com.enigma.ticketing.service.TicketService;
import com.enigma.ticketing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final TicketService ticketService;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findbyId(String id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking create(BookingRequest bookingRequest) {
        User user = userService.getUserByUserId(bookingRequest.getCustomerId());
        Ticket ticket = ticketService.findbyId(bookingRequest.getTicketId());

        Booking booking = Booking.builder()
                .id(bookingRequest.getBookingId())
                .customer(user)
                .ticket(ticket)
                .numberOfTickets(bookingRequest.getNumberOfTickets())
                .totalPrice(bookingRequest.getTotalPrice())
                .orderDate(Date.valueOf(LocalDate.now()))
                .build();

        return bookingRepository.save(booking);
    }
}
