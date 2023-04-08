package tech.getarrays.EncadrantMicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.EncadrantMicroservice.model.Sujet;

import java.util.Optional;

public interface SujetRepo extends JpaRepository<Sujet, Long> {
    void deleteSujetById(Long id);

    Optional<Sujet> findSujetById(Long id);
}
