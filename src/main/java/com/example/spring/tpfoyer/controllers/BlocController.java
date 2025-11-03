package com.example.spring.tpfoyer.controllers;


import com.example.spring.tpfoyer.entity.Bloc;
import com.example.spring.tpfoyer.services.BlocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {
    BlocService blocService;

    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveAllBlocs();
    }

    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        return blocService.addBloc(b);
    }

    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc b) {
        return blocService.modifyBloc(b);
    }
}