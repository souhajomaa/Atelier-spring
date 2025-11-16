package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Etudiant;
import com.example.spring.tpfoyer.services.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Etudiant")
@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {
    EtudiantService etudiantService;

    @Operation(description = "Récupérer tous les étudiants")
    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    @Operation(description = "Récupérer un étudiant par son ID")
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    @Operation(description = "Ajouter un nouvel étudiant")
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantService.addEtudiant(e);
    }

    @Operation(description = "Supprimer un étudiant par son ID")
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        etudiantService.removeEtudiant(etudiantId);
    }

    @Operation(description = "Modifier un étudiant existant")
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant e) {
        return etudiantService.modifyEtudiant(e);
    }
}