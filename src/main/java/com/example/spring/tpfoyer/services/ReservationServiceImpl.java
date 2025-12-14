package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.*;
import com.example.spring.tpfoyer.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    ReservationRepository reservationRepository;
    ChambreRepository chambreRepository;
    EtudiantRepository etudiantRepository;

    BlocRepository blocRepository;
    UniversiteRepository universiteRepository;

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

    @Override
    public Reservation ajouterReservation(long idBloc, long cin) {
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(() -> new RuntimeException("Bloc not found with ID: " + idBloc));
        Etudiant etudiant = etudiantRepository.findById(cin)
                .orElseThrow(() -> new RuntimeException("Étudiant not found with CIN: " + cin));
        Chambre chambreDisponible = trouverChambreDisponible(bloc);

        if (chambreDisponible == null) {
            throw new RuntimeException("Aucune chambre disponible dans le bloc: " + bloc.getNomBloc());
        }
        int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);
        String idReservation = chambreDisponible.getNumeroChambre() + "-" +
                bloc.getNomBloc() + "-" +
                anneeActuelle;

        Reservation reservation = new Reservation();
        reservation.setIdReservation(idReservation);
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);
        reservation.setChambre(chambreDisponible);
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // 7️⃣ Sauvegarder la réservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // 8️⃣ Mettre à jour l'étudiant
        if (etudiant.getReservations() == null) {
            etudiant.setReservations(new HashSet<>());
        }
        etudiant.getReservations().add(savedReservation);
        etudiantRepository.save(etudiant);

        // 9️⃣ Mettre à jour la chambre
        if (chambreDisponible.getReservations() == null) {
            chambreDisponible.setReservations(new HashSet<>());
        }
        chambreDisponible.getReservations().add(savedReservation);
        chambreRepository.save(chambreDisponible);

        return savedReservation;
    }

    private Chambre trouverChambreDisponible(Bloc bloc) {
        // Récupérer toutes les chambres du bloc
        List<Chambre> chambres = new ArrayList<>();
        if (bloc.getChambres() != null) {
            chambres.addAll(bloc.getChambres());
        }

        // Obtenir l'année actuelle
        int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);

        for (Chambre chambre : chambres) {
            int nbReservationsValides = 0;
            if (chambre.getReservations() != null) {
                for (Reservation r : chambre.getReservations()) {
                    if (r.isEstValide()) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(r.getAnneeUniversitaire());
                        if (cal.get(Calendar.YEAR) == anneeActuelle) {
                            nbReservationsValides++;
                        }
                    }
                }
            }

            int capaciteMax = getCapaciteMaxChambre(chambre.getTypeC());

            if (nbReservationsValides < capaciteMax) {
                return chambre;
            }
        }

        return null;
    }

    private int getCapaciteMaxChambre(TypeChambre type) {
        switch (type) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                return 0;
        }
    }
    //sercice 2 annulerReservation

    @Override
    public Reservation annulerReservation(long cin) {
        // 1️⃣ Récupérer l'étudiant par CIN
        Etudiant etudiant = etudiantRepository.findById(cin)
                .orElseThrow(() -> new RuntimeException("Étudiant not found with CIN: " + cin));

        // 2️⃣ Trouver sa réservation valide
        Reservation reservation = null;
        if (etudiant.getReservations() != null) {
            for (Reservation r : etudiant.getReservations()) {
                if (r.isEstValide()) {
                    reservation = r;
                    break;
                }
            }
        }
        if (reservation == null) {
            throw new RuntimeException("Aucune réservation valide trouvée pour cet étudiant");
        }

        // 3️⃣ Mettre à jour l'état de la réservation
        reservation.setEstValide(false);

        // 4️⃣ Désaffecter l'étudiant
        if (reservation.getEtudiants() != null) {
            reservation.getEtudiants().remove(etudiant);
        }
        if (etudiant.getReservations() != null) {
            etudiant.getReservations().remove(reservation);
        }
        // Désaffecter la chambre
        Chambre chambre = reservation.getChambre();
        if (chambre != null) {
            if (chambre.getReservations() != null) {
                chambre.getReservations().remove(reservation);
            }
            chambreRepository.save(chambre);
        }
        reservation.setChambre(null);

        // 6️⃣ Sauvegarder les modifications
        etudiantRepository.save(etudiant);
        return reservationRepository.save(reservation);
    }
    //sercice 3 getReservationParAnneeUniversitaireEtNomUniversite

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {

        // 1️⃣ Récupérer l'université
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université not found: " + nomUniversite));

        // 2️⃣ Extraire l'année de la date
        Calendar calAnnee = Calendar.getInstance();
        calAnnee.setTime(anneeUniversite);
        int annee = calAnnee.get(Calendar.YEAR);

        // 3️⃣ Créer une liste pour stocker les réservations
        List<Reservation> reservationsFiltrees = new ArrayList<>();

        // 4️⃣ Récupérer toutes les réservations
        List<Reservation> toutesReservations = reservationRepository.findAll();

        // 5️⃣ Filtrer les réservations
        for (Reservation reservation : toutesReservations) {
            // Vérifier l'année
            Calendar calRes = Calendar.getInstance();
            calRes.setTime(reservation.getAnneeUniversitaire());
            int anneeReservation = calRes.get(Calendar.YEAR);

            if (anneeReservation != annee) {
                continue;
            }

            // Vérifier l'université
            if (reservation.getChambre() != null &&
                    reservation.getChambre().getBloc() != null &&
                    reservation.getChambre().getBloc().getFoyer() != null &&
                    reservation.getChambre().getBloc().getFoyer().getUniversite() != null &&
                    reservation.getChambre().getBloc().getFoyer().getUniversite().getIdUniversite()
                            .equals(universite.getIdUniversite())) {

                reservationsFiltrees.add(reservation);
            }
        }

        return reservationsFiltrees;
    }
}