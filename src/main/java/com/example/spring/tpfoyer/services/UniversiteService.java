package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Universite;
import java.util.List;

public interface UniversiteService {
    List<Universite> retrieveAllUniversites();
    Universite retrieveUniversite(Long universiteId);
    Universite addUniversite(Universite u);
    void removeUniversite(Long universiteId);
    Universite modifyUniversite(Universite universite);

    Universite affecteFoyerToUniversite(Long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(Long idUniversite);
}