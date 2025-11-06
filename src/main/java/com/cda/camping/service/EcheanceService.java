package com.cda.camping.service;

import com.cda.camping.model.Echeance;
import com.cda.camping.repository.EcheanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcheanceService {

    @Autowired
    private EcheanceRepository echeanceRepository;

    public Echeance getEcheance(Integer id) {
        return echeanceRepository.findById(id);
    }

    public List<Echeance> getAllEcheances() {
        return echeanceRepository.findAll();
    }

    public void createEcheance(Echeance echeance) {
        echeanceRepository.save(echeance);
    }

    public void updateEcheance(Echeance echeance) {
        echeanceRepository.update(echeance);
    }

    public void deleteEcheance(Integer id) {
        echeanceRepository.delete(id);
    }

    public List<Echeance> getEcheancesByFacture(Integer factureId) {
        return echeanceRepository.findByFacture(factureId);
    }

    public Echeance registerEcheance(Echeance echeance) {
        echeanceRepository.save(echeance);
        return echeance;
    }
}





