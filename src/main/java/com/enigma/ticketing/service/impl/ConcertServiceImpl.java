package com.enigma.ticketing.service.impl;

import com.enigma.ticketing.entity.Concert;
import com.enigma.ticketing.repository.ConcertRepository;
import com.enigma.ticketing.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;

    @Override
    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    @Override
    public List<Concert> findByName(String concertName) {
        return concertRepository.findByName(concertName);
    }

    @Override
    public Concert findById(String id) {
        return concertRepository.findById(id);
    }

    @Override
    public Concert crete(Concert concert) {
        return concertRepository.save(concert);
    }

    @Override
    public Concert update(Concert concert) {
        return concertRepository.save(concert);
    }

    @Override
    public void delete(String id) {
    concertRepository.delete(id);
    }
}
