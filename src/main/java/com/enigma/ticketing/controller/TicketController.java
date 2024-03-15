package com.enigma.ticketing.controller;

import com.enigma.ticketing.dto.request.TicketRequest;
import com.enigma.ticketing.dto.response.CommonResponse;
import com.enigma.ticketing.dto.response.TicketResponse;
import com.enigma.ticketing.mapper.TicketMapper;
import com.enigma.ticketing.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<?> getALl() {
        var ticketResponse = ticketService
                .findAll()
                .stream()
                .map(TicketMapper::toTicketResponse)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<List<TicketResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get All Ticket Success")
                        .data(ticketResponse)
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TicketRequest ticketRequest) {
        var ticketResponse = TicketMapper.toTicketResponse(ticketService.create(ticketRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<TicketResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Add Ticket Success")
                        .data(ticketResponse)
                        .build());
    }
}
