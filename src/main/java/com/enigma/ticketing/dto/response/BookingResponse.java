package com.enigma.ticketing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingResponse {
    private Integer numberOfTickets;
    private Long totalPrice;
    private Date orderDate;
    private String customerId;
    private String ticketId;
}
