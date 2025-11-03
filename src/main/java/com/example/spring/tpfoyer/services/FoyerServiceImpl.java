package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Foyer;
import com.example.spring.tpfoyer.repository.FoyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements FoyerService {
    FoyerRepository foyerRepository;

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
}