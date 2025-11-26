package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Bloc;
import com.example.spring.tpfoyer.repository.BlocRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements BlocService {
    BlocRepository blocRepository;

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
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow();
        return  null;
    }
}