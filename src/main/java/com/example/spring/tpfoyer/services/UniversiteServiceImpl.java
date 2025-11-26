package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Foyer;
import com.example.spring.tpfoyer.entity.Universite;
import com.example.spring.tpfoyer.repository.FoyerRepository;
import com.example.spring.tpfoyer.repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService {

    private UniversiteRepository universiteRepository;
    private FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId).orElse(null);
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public void removeUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);
    }

    @Override
    public Universite modifyUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite affecteFoyerToUniversite(Long idFoyer, String nameUniversite) {

        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer not found : " + idFoyer));

        Universite universite = universiteRepository.findByNomUniversite(nameUniversite)
                .orElseThrow(() -> new RuntimeException("Université not found : " + nameUniversite));

        if (foyer.getUniversite() != null) {
            throw new RuntimeException("Ce foyer est déjà affecté à une université.");
        }

        if (universite.getFoyer() != null) {
            throw new RuntimeException("Cette université a déjà un foyer.");
        }

        universite.setFoyer(foyer);
        foyer.setUniversite(universite);

        foyerRepository.save(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {

        Universite universite = universiteRepository.findByIdUniversite(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université not found : " + idUniversite));

        Foyer foyer = universite.getFoyer();

        if (foyer == null) {
            throw new RuntimeException("Cette université n'a pas de foyer affecté.");
        }

        universite.setFoyer(null);
        foyer.setUniversite(null);

        universiteRepository.save(universite);
        foyerRepository.save(foyer);

        return universite;
    }
}
