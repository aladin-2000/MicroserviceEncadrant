package tech.getarrays.EncadrantMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.EncadrantMicroservice.exception.UserNotFoundException;
import tech.getarrays.EncadrantMicroservice.model.SujetAcadimique;
import tech.getarrays.EncadrantMicroservice.repo.SujetAcadimiqueRepo;


import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class SujetAcadimiqueService {
    private final SujetAcadimiqueRepo sujetAcadimiqueRepo;


    @Autowired
    public SujetAcadimiqueService(SujetAcadimiqueRepo sujetAcadimiqueRepo) {
        this.sujetAcadimiqueRepo = sujetAcadimiqueRepo;
    }

    public SujetAcadimique addSujet(SujetAcadimique sujetAcadimique) {
        sujetAcadimique.setTaken(false);

        return sujetAcadimiqueRepo.save(sujetAcadimique);
    }

    public List<SujetAcadimique> findAllSujets() {
        return sujetAcadimiqueRepo.findAll();
    }

    public SujetAcadimique updateSujet(SujetAcadimique sujetAcadimique) {

        sujetAcadimique.setTaken(false);

        return sujetAcadimiqueRepo.save(sujetAcadimique);
    }

    public SujetAcadimique findSujetById(Long id) {
        return sujetAcadimiqueRepo.findSujetById(id)
                .orElseThrow(() -> new UserNotFoundException("Sujet not found"));
    }

    public void deleteSujet(Long id){
        sujetAcadimiqueRepo.deleteSujetAcadimiqueById(id);
    }

    public void validerSujet(Long id,Long idEncadrant){

        SujetAcadimique sujetAcadimique;
        sujetAcadimique = sujetAcadimiqueRepo.findSujetById(id).orElseThrow(() -> new UserNotFoundException("Sujet not found"));
        sujetAcadimique.setTaken(true);
    }
    public List<SujetAcadimique> findByFilere(String filere) {
        return sujetAcadimiqueRepo.findByFilere(filere);
    }

    public List<SujetAcadimique> findSujetAcadimiqueNonDisponible() {
        return sujetAcadimiqueRepo.findAllTakenSujetAcadimique();
    }
    public List<SujetAcadimique> findSujetAcadimiqueDisponible() {
        return sujetAcadimiqueRepo.findAllNoneTakenSujetAcadimique();
    }

    public List<SujetAcadimique> findSujetAcadimiqueByIdEncadrant(Long idEncadrant) {
        return sujetAcadimiqueRepo.findSujetAcadimiqueByIdEncadrant(idEncadrant);
    }







}
