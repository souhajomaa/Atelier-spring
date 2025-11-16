package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Universite;
import com.example.spring.tpfoyer.services.UniversiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteController {
    UniversiteService universiteService;

    @Operation(description = "Récupérer toutes les universités")
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    @Operation(description = "Récupérer une université par son ID")
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    @Operation(description = "Ajouter une nouvelle université")
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @Operation(description = "Supprimer une université par son ID")
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long universiteId) {
        universiteService.removeUniversite(universiteId);
    }

    @Operation(description = "Modifier une université existante")
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.modifyUniversite(u);
    }
}