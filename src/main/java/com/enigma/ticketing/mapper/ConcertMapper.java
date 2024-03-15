package com.enigma.ticketing.mapper;

import com.enigma.ticketing.dto.request.ConcertRequest;
import com.enigma.ticketing.dto.response.ConcertResponse;
import com.enigma.ticketing.entity.Concert;

public class ConcertMapper {

    public static ConcertResponse toConcertResponse(Concert concert) {
        return ConcertResponse.builder()
                .concertName(concert.getConcertName())
                .concertDate(concert.getConcertDate())
                .location(concert.getLocation())
                .capacity(concert.getCapacity())
                .build();
    }

    public static Concert toConcert(ConcertRequest concertRequest) {
        return Concert.builder()
                .id(concertRequest.getId())
                .concertName(concertRequest.getConcertName())
                .concertDate(concertRequest.getConcertDate())
                .location(concertRequest.getLocation())
                .capacity(concertRequest.getCapacity())
                .build();
    }
}
