package com.enigma.ticketing.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ConcertRequest {
    private String id;
    private String concertName;
    private Date concertDate;
    private String location;
    private Integer capacity;
}
