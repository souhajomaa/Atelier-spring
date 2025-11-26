package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    Optional<Universite> findByNomUniversite(String nomUniversite);
    Optional<Universite> findByIdUniversite(Long idUniversite);
}
