package com.enigma.ticketing.service.impl;


import com.enigma.ticketing.dto.request.TicketRequest;
import com.enigma.ticketing.entity.Concert;
import com.enigma.ticketing.entity.Ticket;
import com.enigma.ticketing.repository.TicketRepository;
import com.enigma.ticketing.service.ConcertService;
import com.enigma.ticketing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final ConcertService concertService;
    private final TicketRepository ticketRepository;


    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findbyId(String id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket create(TicketRequest ticketRequest) {
        Concert concert = concertService.findById(ticketRequest.getConcertId());

        Ticket ticket = Ticket.builder()
                .id(ticketRequest.getTicketId())
                .concert(concert)
                .priceTicket(ticketRequest.getPriceTicket())
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
