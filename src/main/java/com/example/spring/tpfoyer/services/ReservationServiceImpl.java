package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Reservation;
import com.example.spring.tpfoyer.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    ReservationRepository reservationRepository;

    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    public void removeReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}