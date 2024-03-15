package com.enigma.ticketing.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phoneNumber;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredentialId;

    @OneToMany(mappedBy = "booking")
    @JsonBackReference
    private List<Booking> booking;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Booking> customerBooking;
}
