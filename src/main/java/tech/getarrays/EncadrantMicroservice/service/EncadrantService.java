package tech.getarrays.EncadrantMicroservice.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.EncadrantMicroservice.exception.UserNotFoundException;
import tech.getarrays.EncadrantMicroservice.model.Encadrant;
import tech.getarrays.EncadrantMicroservice.registration.token.ConfirmationToken;
import tech.getarrays.EncadrantMicroservice.registration.token.ConfirmationTokenService;
import tech.getarrays.EncadrantMicroservice.repo.EncadrantRepo;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EncadrantService {
    private final EncadrantRepo encadrantRepo;
    private final ConfirmationTokenService confirmationTokenService;


    @Autowired
    public EncadrantService(EncadrantRepo encadrantRepo,ConfirmationTokenService confirmationTokenService) {
        this.encadrantRepo = encadrantRepo;
        this.confirmationTokenService=confirmationTokenService;
    }
    public Encadrant addEncadrantt(Encadrant encadrant) {
        encadrant.setPassword(BCrypt.hashpw(encadrant.getPassword(),BCrypt.gensalt()));
        encadrant.setEnabled(true);
        return encadrantRepo.save(encadrant);}

    public String addEncadrant(Encadrant encadrant) {
        encadrant.setPassword(BCrypt.hashpw(encadrant.getPassword(),BCrypt.gensalt()));
         encadrantRepo.save(encadrant);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                encadrant
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public List<Encadrant> findAllEncadrants() {
        return encadrantRepo.findAll();
    }

    public Encadrant updateEncadrant(Encadrant encadrant) {
        encadrant.setPassword(BCrypt.hashpw(encadrant.getPassword(),BCrypt.gensalt()));
        return encadrantRepo.save(encadrant);
    }

    public Encadrant findEncadrantById(Long id) {
        return encadrantRepo.findEncadrantById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }


    public void deleteEncadrant(Long id){
        confirmationTokenService.deleteConfirmationTokenByEncadrant(id);
        encadrantRepo.deleteEncadrantById(id);
    }




    public Encadrant login(String email, String password){
        Encadrant encadrant = encadrantRepo.findEncadrantByEmail(email).orElseThrow(() -> new UserNotFoundException("Email ou mot de passe est incorrecte"));
        if (!( BCrypt.checkpw(password,encadrant.getPassword()) && encadrant.getEnabled())==true) {
            throw new UserNotFoundException("Email ou mot de passe sont incorrecte ");
        }
        return encadrant;
    }

    public int enableEncadrant(String email) {
        return encadrantRepo.enableEncadrant(email);
    }

    public void SujetTaken(Long idSujetAcadimique){
        encadrantRepo.marquerSujetPris(idSujetAcadimique);
    }

    public void NetoyerLaTable(Long idSujetAcadimique){
        encadrantRepo.nettoyerTable(idSujetAcadimique);
    }

    public void ValiderPostulation(Long idEtudiant , Long idSujet){
        encadrantRepo.validerPostulation(idEtudiant,idSujet);
    }

    @Transactional
    public void validerSujetBien(Long idEtudiant, Long idSujetAcadimique) {
        encadrantRepo.validerPostulation(idEtudiant, idSujetAcadimique);
        encadrantRepo.marquerSujetPris(idSujetAcadimique);
        encadrantRepo.nettoyerTable(idSujetAcadimique);
    }



}
