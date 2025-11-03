package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
