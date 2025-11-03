package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Etudiant;
import com.example.spring.tpfoyer.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {
    EtudiantRepository etudiantRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId).orElse(null);
    }

    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }

    public Etudiant modifyEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
}