package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
}
