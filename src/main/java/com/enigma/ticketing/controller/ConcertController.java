package com.enigma.ticketing.controller;


import com.enigma.ticketing.dto.request.ConcertRequest;
import com.enigma.ticketing.dto.response.CommonResponse;
import com.enigma.ticketing.dto.response.ConcertResponse;
import com.enigma.ticketing.mapper.ConcertMapper;
import com.enigma.ticketing.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/concert")
public class ConcertController {
    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<?> getALl() {
        var consertResponse = concertService
                .findAll()
                .stream()
                .map(ConcertMapper::toConcertResponse)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<List<ConcertResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get All Concert Success")
                        .data(consertResponse)
                        .build());
    }

    @GetMapping("/{id_concert}")
    public ResponseEntity<?> getById(@PathVariable String id_concert) {
        var concertResponse = ConcertMapper.toConcertResponse(concertService.findById(id_concert));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<ConcertResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Concert Success")
                        .data(concertResponse)
                        .build());
    }

    @GetMapping(params = {"concertName"})
    public ResponseEntity<?> getByName (@RequestParam(name = "concertName", required = false) String concertName) {
        var concertResponse = concertService
                .findByName(concertName)
                .stream()
                .map(ConcertMapper::toConcertResponse)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<List<ConcertResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Concert Success")
                        .data(concertResponse)
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ConcertRequest concertRequest) {
        var concertResponse = ConcertMapper.toConcertResponse(concertService.crete(ConcertMapper.toConcert(concertRequest)));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<ConcertResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Add Concert Success")
                        .data(concertResponse)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ConcertRequest concertRequest) {
        var concertResponse = ConcertMapper.toConcertResponse(concertService.update(ConcertMapper.toConcert(concertRequest)));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<ConcertResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Update Concert Success")
                        .data(concertResponse)
                        .build());
    }

    @DeleteMapping("/{id_concert}")
    public ResponseEntity<?> delete(@PathVariable String id_concert) {
        concertService.delete(id_concert);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(null)
                        .build());
    }


}
