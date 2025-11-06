package com.cda.camping.service;

import com.cda.camping.model.Hebergement;
import com.cda.camping.repository.HebergementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HebergementService {

    @Autowired
    private HebergementRepository hebergementRepository;

    public Hebergement getHebergement(Integer id) {
        return hebergementRepository.findById(id);
    }

    public List<Hebergement> getAllHebergements() {
        return hebergementRepository.findAll();
    }

    public void createHebergement(Hebergement hebergement) {
        hebergementRepository.save(hebergement);
    }

    public void updateHebergement(Hebergement hebergement) {
        hebergementRepository.update(hebergement);
    }

    public void deleteHebergement(Integer id) {
        hebergementRepository.delete(id);
    }

    public List<Hebergement> getHebergementsByType(Integer typeId) {
        return hebergementRepository.findByType(typeId);
    }

    public Hebergement registerHebergement(Hebergement hebergement) {
        hebergementRepository.save(hebergement);
        return hebergement;
    }
}






