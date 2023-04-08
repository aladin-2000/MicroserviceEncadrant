package tech.getarrays.EncadrantMicroservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.EncadrantMicroservice.model.Encadrant;

import java.util.Optional;

public interface EncadrantRepo extends JpaRepository<Encadrant, Long> {
    void deleteEncadrantById(Long id);

    Optional<Encadrant> findEncadrantById(Long id);

    Optional<Encadrant> findEncadrantByEmail(String email);



}
