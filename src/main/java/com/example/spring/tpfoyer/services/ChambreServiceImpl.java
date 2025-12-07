package com.example.spring.tpfoyer.services;


import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService{
    ChambreRepository chambreRepository;
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

}
