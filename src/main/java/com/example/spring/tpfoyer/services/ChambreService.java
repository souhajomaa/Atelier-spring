package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface ChambreService {
    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);
    public Chambre modifyChambre(Chambre chambre);
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);


}
