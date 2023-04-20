package tech.getarrays.EncadrantMicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.EncadrantMicroservice.model.Encadrant;

import java.util.Optional;

public interface EncadrantRepo extends JpaRepository<Encadrant, Long> {
    void deleteEncadrantById(Long id);

    Optional<Encadrant> findEncadrantById(Long id);

    Optional<Encadrant> findEncadrantByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Encadrant a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableEncadrant(String email);


    @Transactional
    @Modifying
    @Query("UPDATE PostulationSujetAcadimique a " +
            "SET a.Valide = TRUE WHERE a.idEtudiant = ?1 AND a.idSujet = ?2")
    void validerPostulation(Long idEtudiant, Long idSujet);

    @Transactional
    @Modifying
    @Query("UPDATE SujetAcadimique a SET a.taken = TRUE WHERE a.id = ?1")
    void marquerSujetPris(Long idSujet);

    @Transactional
    @Modifying
    @Query("DELETE FROM PostulationSujetAcadimique p WHERE p.idSujet = ?1 AND p.Valide = false")
    void nettoyerTable(Long idSujetAcadimique);



}
