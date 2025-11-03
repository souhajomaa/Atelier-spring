package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
