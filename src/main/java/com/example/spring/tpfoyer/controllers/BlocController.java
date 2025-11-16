package com.example.spring.tpfoyer.controllers;


import com.example.spring.tpfoyer.entity.Bloc;
import com.example.spring.tpfoyer.services.BlocService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Gestion Bloc")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {
    BlocService blocService;

    @Operation(description = "Récupérer tous les blocs")
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveAllBlocs();
    }

    @Operation(description = "Récupérer un bloc par son ID")
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    @Operation(description = "Ajouter un nouveau bloc")
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        return blocService.addBloc(b);
    }

    @Operation(description = "Supprimer un bloc par son ID")
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    @Operation(description = "Modifier un bloc existant")
    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc b) {
        return blocService.modifyBloc(b);
    }
}