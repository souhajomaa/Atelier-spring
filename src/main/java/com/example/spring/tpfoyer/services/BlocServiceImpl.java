package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Bloc;
import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.repository.BlocRepository;
import com.example.spring.tpfoyer.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements BlocService {

    BlocRepository blocRepository;
    ChambreRepository chambreRepository;

    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId).orElse(null);
    }

    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc not found: " + idBloc));

        List<Chambre> chambres = new ArrayList<>();
        for (Long numeroChambre : numChambre) {
            Chambre chambre = chambreRepository.findByNumeroChambre(numeroChambre)
                    .orElseThrow(() -> new RuntimeException("Chambre not found with numero: " + numeroChambre));

            if (chambre.getBloc().getIdBloc() != idBloc) {
                throw new RuntimeException("Chambre " + numeroChambre +
                        " est déjà affectée au bloc: " + chambre.getBloc().getNomBloc());
            }

            chambre.setBloc(bloc);

            chambres.add(chambre);
        }

      chambreRepository.saveAll(chambres);

        return blocRepository.findById(idBloc).orElse(null);
    }
}