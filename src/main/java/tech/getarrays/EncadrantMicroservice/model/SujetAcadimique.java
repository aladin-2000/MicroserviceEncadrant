package tech.getarrays.EncadrantMicroservice.model;



import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class SujetAcadimique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String titre;
    private String detail;
    private Long idEncadrant;
    private boolean taken;
    private String filere;


    public SujetAcadimique() {}


}
