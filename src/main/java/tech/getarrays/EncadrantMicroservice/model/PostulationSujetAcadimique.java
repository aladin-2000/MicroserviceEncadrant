package tech.getarrays.EncadrantMicroservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class PostulationSujetAcadimique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Long idSujet;
    private Long idEtudiant;
    private boolean Valide;

    public PostulationSujetAcadimique(Long idSujet,Long idEtudiant) {


        this.idSujet = idSujet;
        this.idEtudiant = idEtudiant;
    }
    public PostulationSujetAcadimique() {}





}
