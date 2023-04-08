package tech.getarrays.EncadrantMicroservice.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.EncadrantMicroservice.model.Sujet;
import tech.getarrays.EncadrantMicroservice.service.SujetService;


import java.util.List;

@RestController
@RequestMapping("/Sujet")
public class SujetController {
    private final SujetService sujetService;

    public SujetController(SujetService sujetService) {
        this.sujetService = sujetService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sujet>> getAllSujets () {
        List<Sujet> sujets = sujetService.findAllSujets();
        return new ResponseEntity<>(sujets, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Sujet> getSujetById (@PathVariable("id") Long id) {
        Sujet sujet = sujetService.findSujetById(id);
        return new ResponseEntity<>(sujet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Sujet> addSujet(@RequestBody Sujet sujet) {
        Sujet newSujet = sujetService.addSujet(sujet);
        return new ResponseEntity<>(newSujet, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Sujet> updateSujet(@RequestBody Sujet sujet) {
        Sujet updateSujet = sujetService.updateSujet(sujet);
        return new ResponseEntity<>(updateSujet, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSujet(@PathVariable("id") Long id) {
        sujetService.deleteSujet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
