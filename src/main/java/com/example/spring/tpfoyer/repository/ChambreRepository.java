package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    Optional<Chambre> findByNumeroChambre(Long numeroChambre);

}
