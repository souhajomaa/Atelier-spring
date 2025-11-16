package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Foyer;
import com.example.spring.tpfoyer.services.FoyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {
    FoyerService foyerService;

    @Operation(description = "Récupérer tous les foyers")
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        return foyerService.retrieveAllFoyers();
    }

    @Operation(description = "Récupérer un foyer par son ID")
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long foyerId) {
        return foyerService.retrieveFoyer(foyerId);
    }


    @Operation(description = "Ajouter un nouveau foyer")
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }

    @Operation(description = "Supprimer un foyer par son ID")
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long foyerId) {
        foyerService.removeFoyer(foyerId);
    }

    @Operation(description = "Modifier un foyer existant")
    @PutMapping("/modify-foyer")
    public Foyer modifyFoyer(@RequestBody Foyer f) {
        return foyerService.modifyFoyer(f);
    }
}