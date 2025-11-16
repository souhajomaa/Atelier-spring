package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Reservation;
import com.example.spring.tpfoyer.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    ReservationService reservationService;

    @Operation(description = "Récupérer toutes les réservations")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    @Operation(description = "Récupérer une réservation par son ID")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    @Operation(description = "Ajouter une nouvelle réservation")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        return reservationService.addReservation(r);
    }

    @Operation(description = "Supprimer une réservation par son ID")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String reservationId) {
        reservationService.removeReservation(reservationId);
    }

    @Operation(description = "Modifier une réservation existante")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        return reservationService.modifyReservation(r);
    }
}