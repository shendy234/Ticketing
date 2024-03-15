package com.enigma.ticketing.service;

import com.enigma.ticketing.entity.Concert;

import java.util.List;

public interface ConcertService {

    List<Concert> findAll();
    List<Concert> findByName(String concertName);
    Concert findById(String id);
    Concert crete(Concert concert);
    Concert update(Concert concert);
    void delete(String id);
}
