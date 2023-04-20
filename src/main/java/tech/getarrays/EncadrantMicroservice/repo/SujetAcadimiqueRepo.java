package tech.getarrays.EncadrantMicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.getarrays.EncadrantMicroservice.model.SujetAcadimique;

import java.util.List;
import java.util.Optional;

public interface SujetAcadimiqueRepo extends JpaRepository<SujetAcadimique, Long> {
    void deleteSujetAcadimiqueById(Long id);

    Optional<SujetAcadimique> findSujetById(Long id);
    @Query("SELECT s FROM SujetAcadimique s WHERE s.filere = ?1")
    List<SujetAcadimique> findByFilere(String filere);

    @Query("SELECT s FROM SujetAcadimique s WHERE s.taken = true")
    List<SujetAcadimique> findAllTakenSujetAcadimique();
    @Query("SELECT s FROM SujetAcadimique s WHERE s.taken = false")
    List<SujetAcadimique> findAllNoneTakenSujetAcadimique();

    @Query("SELECT s FROM SujetAcadimique s WHERE s.idEncadrant = ?1")
    List<SujetAcadimique> findSujetAcadimiqueByIdEncadrant(Long idEncadrant);








}
