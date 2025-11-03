package com.example.spring.tpfoyer.repository;

import com.example.spring.tpfoyer.entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
}
