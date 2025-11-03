package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Reservation;
import com.example.spring.tpfoyer.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    ReservationService reservationService;

    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        return reservationService.addReservation(r);
    }

    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String reservationId) {
        reservationService.removeReservation(reservationId);
    }

    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        return reservationService.modifyReservation(r);
    }
}