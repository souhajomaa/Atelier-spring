package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Chambre;
import com.example.spring.tpfoyer.services.ChambreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Gestion Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreController {

    ChambreService chambreService;

    @Operation(description = "Récupérer une chambre par son ID")
    // GET http://localhost:8081/tpfoyer/chambre/retrieve-all-chambres
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
       return chambreService.retrieveAllChambres();

    }
    @Operation(description = "Récupérer une chambre par son ID")
    // GET http://localhost:8081/tpfoyer/retrieve-chambre/2
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        return chambreService.retrieveChambre(chId);
    }
    @Operation(description = "Ajouter une nouvelle chambre")
    // POST : http://localhost:8081/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        return chambreService.addChambre(c);
    }

    @Operation(description = "Supprimer une chambre par son ID")
    // DELETE : Supprimer une chambre
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    @Operation(description = "Modifier une chambre existante")
    // PUT : Modifier une chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        return chambreService.modifyChambre(c);
    }
}
