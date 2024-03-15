package com.enigma.ticketing.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "m_concert")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String concertName;
    private Date concertDate;
    private String location;
    private Integer capacity;

    @OneToMany(mappedBy = "concert")
    @JsonBackReference
    private List<Ticket> Ticket;
}
