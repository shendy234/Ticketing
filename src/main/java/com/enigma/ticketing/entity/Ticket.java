package com.enigma.ticketing.entity;


import com.enigma.ticketing.constant.ETicketStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "m_ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long priceTicket;

    @Enumerated(EnumType.STRING)
    private ETicketStatus status;

    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @OneToMany(mappedBy = "ticket")
    @JsonBackReference
    private List<Booking> booking;
}
