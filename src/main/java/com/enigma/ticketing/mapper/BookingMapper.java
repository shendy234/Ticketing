package com.enigma.ticketing.mapper;

import com.enigma.ticketing.dto.request.BookingRequest;
import com.enigma.ticketing.dto.response.BookingResponse;
import com.enigma.ticketing.entity.Booking;
import com.enigma.ticketing.entity.Ticket;
import com.enigma.ticketing.entity.User;

public class BookingMapper {

    public static BookingResponse toBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .numberOfTickets(booking.getNumberOfTickets())
                .totalPrice(booking.getTotalPrice())
                .orderDate(booking.getOrderDate())
                .customerId(booking.getCustomer().getId())
                .ticketId(booking.getTicket().getId())
                .build();
    }

    public static Booking toBooking(BookingRequest bookingRequest) {
        return Booking.builder()
                .id(bookingRequest.getBookingId())
                .numberOfTickets(bookingRequest.getNumberOfTickets())
                .totalPrice(bookingRequest.getTotalPrice())
                .orderDate(bookingRequest.getOrderDate())
                .customer(User.builder()
                        .id(bookingRequest.getCustomerId())
                        .build())
                .ticket(Ticket.builder()
                        .id(bookingRequest.getTicketId())
                        .build())
                .build();
    }
}
