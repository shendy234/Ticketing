package com.enigma.ticketing.service;

import com.enigma.ticketing.dto.request.BookingRequest;
import com.enigma.ticketing.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findAll();
    Booking findbyId(String id);
    Booking create (BookingRequest bookingRequest);
}
