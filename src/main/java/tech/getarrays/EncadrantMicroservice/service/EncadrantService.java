package tech.getarrays.EncadrantMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.EncadrantMicroservice.exception.UserNotFoundException;
import tech.getarrays.EncadrantMicroservice.model.Encadrant;
import tech.getarrays.EncadrantMicroservice.repo.EncadrantRepo;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EncadrantService {
    private final EncadrantRepo encadrantRepo;

    @Autowired
    public EncadrantService(EncadrantRepo encadrantRepo) {
        this.encadrantRepo = encadrantRepo;
    }

    public Encadrant addEncadrant(Encadrant encadrant) {
        return encadrantRepo.save(encadrant);
    }

    public List<Encadrant> findAllEncadrants() {
        return encadrantRepo.findAll();
    }

    public Encadrant updateEncadrant(Encadrant encadrant) {
        return encadrantRepo.save(encadrant);
    }

    public Encadrant findEncadrantById(Long id) {
        return encadrantRepo.findEncadrantById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }


    public void deleteEncadrant(Long id){
        encadrantRepo.deleteEncadrantById(id);
    }
    public Encadrant login(String email, String password){
        Encadrant encadrant = encadrantRepo.findEncadrantByEmail(email).orElseThrow(() -> new UserNotFoundException("Email ou mot de passe sont incorrecte"));
        if (!password.equals(encadrant.getPassword())) {
            throw new UserNotFoundException("Email ou mot de passe sont incorrecte ");
        }
        return encadrant;


    }
}
