package tech.getarrays.EncadrantMicroservice.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.EncadrantMicroservice.model.SujetAcadimique;
import tech.getarrays.EncadrantMicroservice.service.SujetAcadimiqueService;


import java.util.List;

@RestController
@RequestMapping("/SujetAcadimique")
public class SujetController {
    private final SujetAcadimiqueService sujetAcadimiqueService;

    public SujetController(SujetAcadimiqueService sujetAcadimiqueService) {
        this.sujetAcadimiqueService = sujetAcadimiqueService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SujetAcadimique>> getAllSujets () {
        List<SujetAcadimique> sujetAcadimiques = sujetAcadimiqueService.findAllSujets();
        return new ResponseEntity<>(sujetAcadimiques, HttpStatus.OK);
    }

    @GetMapping("/find/SujetAcadimiqueById/{id}")
    public ResponseEntity<SujetAcadimique> getSujetById (@PathVariable("id") Long id) {
        SujetAcadimique sujetAcadimique = sujetAcadimiqueService.findSujetById(id);
        return new ResponseEntity<>(sujetAcadimique, HttpStatus.OK);
    }



    @GetMapping("/disponible")
    public List<SujetAcadimique> getTakenSujets() {
        return sujetAcadimiqueService.findSujetAcadimiqueDisponible();
    }
    @GetMapping("/non-disponible")
    public List<SujetAcadimique> getNoneTakenSujets() {
        return sujetAcadimiqueService.findSujetAcadimiqueNonDisponible();
    }


    @GetMapping("/filiere/{filere}")
    public ResponseEntity<List<SujetAcadimique>> findByFilere(@PathVariable String filere) {
        List<SujetAcadimique> sujetAcadimiques = sujetAcadimiqueService.findByFilere(filere);
        return new ResponseEntity<>(sujetAcadimiques, HttpStatus.OK);
    }


    @GetMapping("/Encadrant/{idEncadrant}")
    public ResponseEntity<List<SujetAcadimique>> findSujetAcadimiqueByEncadrant(@PathVariable Long idEncadrant) {
        List<SujetAcadimique> sujetAcadimiques = sujetAcadimiqueService.findSujetAcadimiqueByIdEncadrant(idEncadrant);
        return new ResponseEntity<>(sujetAcadimiques, HttpStatus.OK);
    }





    @PostMapping("/add")
    public ResponseEntity<SujetAcadimique> addSujet(@RequestBody SujetAcadimique sujetAcadimique) {
        SujetAcadimique newSujetAcadimique = sujetAcadimiqueService.addSujet(sujetAcadimique);
        return new ResponseEntity<>(newSujetAcadimique, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SujetAcadimique> updateSujet(@RequestBody SujetAcadimique sujetAcadimique) {
        SujetAcadimique updateSujetAcadimique = sujetAcadimiqueService.updateSujet(sujetAcadimique);
        return new ResponseEntity<>(updateSujetAcadimique, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSujet(@PathVariable("id") Long id) {
        sujetAcadimiqueService.deleteSujet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
