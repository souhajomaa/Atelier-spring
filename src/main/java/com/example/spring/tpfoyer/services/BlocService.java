package com.example.spring.tpfoyer.services;

import com.example.spring.tpfoyer.entity.Bloc;

import java.util.List;

public interface BlocService {
    List<Bloc> retrieveAllBlocs();
    Bloc retrieveBloc(Long blocId);
    Bloc addBloc(Bloc b);
    void removeBloc(Long blocId);
    Bloc modifyBloc(Bloc bloc);
    public Bloc affecterChambresABloc(List<Long> numChambre, long  idBloc);
}
