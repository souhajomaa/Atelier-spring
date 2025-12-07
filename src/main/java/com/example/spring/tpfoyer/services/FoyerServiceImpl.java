package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Bloc;
import com.example.spring.tpfoyer.entity.Foyer;
import com.example.spring.tpfoyer.entity.Universite;
import com.example.spring.tpfoyer.repository.FoyerRepository;
import com.example.spring.tpfoyer.repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements FoyerService {
    FoyerRepository foyerRepository;
    UniversiteRepository universiteRepository;


    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId).orElse(null);
    }

    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }

    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    //ajouterFoyerEtAffecterAUniversite

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université not found with ID: " + idUniversite));

        if (universite.getFoyer() != null) {
            throw new RuntimeException("Cette université a déjà un foyer affecté");
        }

        List<Bloc> blocs = new ArrayList<>();

        if (foyer.getBlocs() != null) {
            for (Bloc bloc : foyer.getBlocs()) {
                bloc.setFoyer(foyer);
                blocs.add(bloc);
            }
        }

        Foyer savedFoyer = foyerRepository.save(foyer);

        universite.setFoyer(savedFoyer);
        savedFoyer.setUniversite(universite);

        universiteRepository.save(universite);

        return savedFoyer;
    }
}