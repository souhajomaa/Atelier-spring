package com.example.spring.tpfoyer.controllers;

import com.example.spring.tpfoyer.entity.Universite;
import com.example.spring.tpfoyer.services.UniversiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteController {
    UniversiteService universiteService;

    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long universiteId) {
        universiteService.removeUniversite(universiteId);
    }

    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.modifyUniversite(u);
    }
}
