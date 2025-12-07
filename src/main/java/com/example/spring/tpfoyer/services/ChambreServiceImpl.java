package com.example.spring.tpfoyer.services;


import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService{
    @Autowired
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
    //Scheduled
    @Scheduled(cron = "0/15 * * * * *")
    public void pourcentageChambreParTypeChambre() {
        List<Chambre> chambreList = chambreRepository.findAll();
        int total = chambreList.size();
        log.info("nombre de chambres:"+ total);
    }
}
