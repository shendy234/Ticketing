package com.enigma.ticketing.controller;


import com.enigma.ticketing.dto.request.BookingRequest;
import com.enigma.ticketing.dto.response.BookingResponse;
import com.enigma.ticketing.dto.response.CommonResponse;
import com.enigma.ticketing.mapper.BookingMapper;
import com.enigma.ticketing.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/Booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> Booking(@RequestBody BookingRequest bookingRequest) {
        var bookingResponse = BookingMapper.toBookingResponse(bookingService.create(bookingRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<BookingResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Create Issue Success")
                        .data(bookingResponse)
                        .build());
    }
}
