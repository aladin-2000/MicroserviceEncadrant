package tech.getarrays.EncadrantMicroservice.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.EncadrantMicroservice.model.Encadrant;
import tech.getarrays.EncadrantMicroservice.service.EncadrantService;


import java.util.List;

@RestController
@RequestMapping("/Encadrant")
public class EncadrantController {
    private final EncadrantService encadrantService;

    public EncadrantController(EncadrantService encadrantService) {
        this.encadrantService = encadrantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Encadrant>> getAllEncadrant () {
        List<Encadrant> encadrants = encadrantService.findAllEncadrants();
        return new ResponseEntity<>(encadrants, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Encadrant> getEncadrantById (@PathVariable("id") Long id) {
        Encadrant encadrant = encadrantService.findEncadrantById(id);
        return new ResponseEntity<>(encadrant, HttpStatus.OK);
    }



    @PostMapping("/addEncadrant")
    public ResponseEntity<Encadrant> addEncadrant(@RequestBody Encadrant encadrant) {
        Encadrant newEncadrant = encadrantService.addEncadrantt(encadrant);
        return new ResponseEntity<>(newEncadrant, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Encadrant> updateEncadrant(@RequestBody Encadrant encadrant) {
        Encadrant updateEncadrant = encadrantService.updateEncadrant(encadrant);
        return new ResponseEntity<>(updateEncadrant, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEncadrant(@PathVariable("id") Long id) {
        encadrantService.deleteEncadrant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Encadrant encadrant)
    {
        String email=encadrant.getEmail();
        String password=encadrant.getPassword();
        return new ResponseEntity<>(encadrantService.login(email,password), HttpStatus.OK);
    }

    @PostMapping("ValiderPostulation/{idEtudiant}/{idSujetAcadimique}")
    public ResponseEntity<String> validerSujet(
            @PathVariable Long idEtudiant,
            @PathVariable Long idSujetAcadimique) {

        try {
            encadrantService.validerSujetBien(idEtudiant, idSujetAcadimique);
            return ResponseEntity.ok("Sujet validé avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la validation du sujet : " + e.getMessage());
        }
    }

}
