package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Universite;
import com.example.spring.tpfoyer.repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService {
    UniversiteRepository universiteRepository;

    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId).orElse(null);
    }

    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    public void removeUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);
    }

    public Universite modifyUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }
}