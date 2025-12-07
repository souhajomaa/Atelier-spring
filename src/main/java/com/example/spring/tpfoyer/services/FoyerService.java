package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Foyer;
import java.util.List;

public interface FoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer retrieveFoyer(Long foyerId);
    Foyer addFoyer(Foyer f);
    void removeFoyer(Long foyerId);
    Foyer modifyFoyer(Foyer foyer);
    Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, Long idUniversite);

}