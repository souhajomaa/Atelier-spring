package com.example.spring.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String idReservation;

    private Date anneeUniversitaire;
    private boolean estValide;
    @ManyToOne
    private Chambre chambre;

    @ManyToMany(mappedBy = "reservations")
    private Set<Etudiant> etudiants;
}