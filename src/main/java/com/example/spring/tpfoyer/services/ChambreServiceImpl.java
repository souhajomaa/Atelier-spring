package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.entity.Reservation;
import com.example.spring.tpfoyer.entity.TypeChambre;
import com.example.spring.tpfoyer.entity.Universite;
import com.example.spring.tpfoyer.repository.BlocRepository;
import com.example.spring.tpfoyer.repository.ChambreRepository;
import com.example.spring.tpfoyer.repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    ChambreRepository chambreRepository;
    UniversiteRepository universiteRepository;
    BlocRepository blocRepository;
    List<Chambre> chambreList = new ArrayList<>();


    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).orElse(null);
    }

    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void pourcentageChambreParTypeChambre() {
        chambreList = chambreRepository.findAll();
        int totalChambres = chambreList.size();

        log.info("nombre de chambres : " + totalChambres);


        if (totalChambres > 0 && chambreList != null) {

            Map<String, Integer> countByType = new HashMap<>();

            for (Chambre chambre : chambreList) {
                String type = String.valueOf(chambre.getTypeC());
                countByType.put(type, countByType.getOrDefault(type, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : countByType.entrySet()) {

                String type = entry.getKey();
                int count = entry.getValue();
                double pourcentage = (count * 100.0) / totalChambres;
                String resultat = String.format("%.2f", pourcentage);

                log.info("pourcentage des chambres pour le type : " + type + " est égal à " + resultat);
            }
        } else {
            log.info("  Aucune chambre disponible pour le calcul des pourcentages");
        }
    }

        @Override
        public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre (String nomUniversite, TypeChambre type)
        {

            // 1️⃣ Récupérer l'université
            Universite universite = universiteRepository.findByNomUniversite(nomUniversite)
                    .orElseThrow(() -> new RuntimeException("Université not found: " + nomUniversite));

            // 2️⃣ Obtenir l'année actuelle
            int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);

            // 3️⃣ Créer une liste pour stocker les chambres non réservées
            List<Chambre> chambresNonReservees = new ArrayList<>();

            // 4️⃣ Récupérer toutes les chambres
            List<Chambre> toutesChambres = chambreRepository.findAll();

            // 5️⃣ Filtrer les chambres
            for (Chambre chambre : toutesChambres) {

                // Vérifier le type
                if (chambre.getTypeC() != type) {
                    continue;
                }

                // Vérifier l'université
                if (chambre.getBloc() == null ||
                        chambre.getBloc().getFoyer() == null ||
                        chambre.getBloc().getFoyer().getUniversite() == null ||
                        !chambre.getBloc().getFoyer().getUniversite().getIdUniversite()
                                .equals(universite.getIdUniversite())) {
                    continue;
                }

                // Vérifier si la chambre n'a pas de réservation valide cette année
                boolean estReservee = false;

                if (chambre.getReservations() != null) {
                    for (Reservation reservation : chambre.getReservations()) {
                        if (reservation.isEstValide()) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(reservation.getAnneeUniversitaire());

                            if (cal.get(Calendar.YEAR) == anneeActuelle) {
                                estReservee = true;
                                break;
                            }
                        }
                    }
                }

                if (!estReservee) {
                    chambresNonReservees.add(chambre);
                }
            }

            return chambresNonReservees;
        }
    }
