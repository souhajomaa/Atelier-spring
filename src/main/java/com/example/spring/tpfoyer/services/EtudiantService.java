package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> retrieveAllEtudiants();
    Etudiant retrieveEtudiant(Long etudiantId);
    Etudiant addEtudiant(Etudiant e);
    void removeEtudiant(Long etudiantId);
    Etudiant modifyEtudiant(Etudiant etudiant);
}
