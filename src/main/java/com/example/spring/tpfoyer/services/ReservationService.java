package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String idReservation);  // ✅ Changé ici
    Reservation addReservation(Reservation r);
    void removeReservation(String idReservation);  // ✅ Changé ici
    Reservation modifyReservation(Reservation reservation);
}