package com.enigma.ticketing.dto.response;

import com.enigma.ticketing.constant.ETicketStatus;
import com.enigma.ticketing.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TicketResponse {
    private Long priceTicket;
    private ETicketStatus ticketStatus;
    private String concertId;
    private List<Booking> bookings;
}
