package com.enigma.ticketing.service;

import com.enigma.ticketing.dto.request.TicketRequest;
import com.enigma.ticketing.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();
    Ticket findbyId(String id);
    Ticket create(TicketRequest ticketRequest);
    Ticket update (Ticket ticket);
}
