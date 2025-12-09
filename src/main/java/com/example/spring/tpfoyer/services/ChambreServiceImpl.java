package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    ChambreRepository chambreRepository;
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
}
