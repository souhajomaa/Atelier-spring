package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
}
