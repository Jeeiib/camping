package com.cda.camping.service;

import com.cda.camping.model.Facture;
import com.cda.camping.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    public Facture getFacture(Integer id) {
        return factureRepository.findById(id);
    }

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public void createFacture(Facture facture) {
        factureRepository.save(facture);
    }

    public void updateFacture(Facture facture) {
        factureRepository.update(facture);
    }

    public void deleteFacture(Integer id) {
        factureRepository.delete(id);
    }

    public Facture getFactureByReservationId(Integer resaId) {
        return factureRepository.findByReservationId(resaId);
    }
}
