package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String idReservation);
    Reservation addReservation(Reservation r);
    void removeReservation(String idReservation);
    Reservation modifyReservation(Reservation reservation);
    Reservation ajouterReservation(long idBloc, long cin);
    Reservation annulerReservation(long cin);
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite);



}