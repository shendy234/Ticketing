package com.enigma.ticketing.mapper;

import com.enigma.ticketing.dto.request.TicketRequest;
import com.enigma.ticketing.dto.response.TicketResponse;
import com.enigma.ticketing.entity.Concert;
import com.enigma.ticketing.entity.Ticket;

public class TicketMapper {

    public static TicketResponse toTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .priceTicket(ticket.getPriceTicket())
                .ticketStatus(ticket.getStatus())
                .concertId(ticket.getConcert().getId())
                .build();
    }

    public static Ticket toTicket(TicketRequest ticketRequest) {
        return Ticket.builder()
                .id(ticketRequest.getTicketId())
                .priceTicket(ticketRequest.getPriceTicket())
                .status(ticketRequest.getTicketStatus())
                .concert(Concert.builder()
                        .id(ticketRequest.getConcertId())
                        .build())
                .build();
    }
}
