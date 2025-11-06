package com.cda.camping.service;

import com.cda.camping.model.FactureService;
import com.cda.camping.repository.FactureServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureServiceService {

    @Autowired
    private FactureServiceRepository factureServiceRepository;

    public FactureService getFactureService(Integer id) {
        return factureServiceRepository.findById(id);
    }

    public List<FactureService> getAllFactureServices() {
        return factureServiceRepository.findAll();
    }

    public void createFactureService(FactureService factureService) {
        factureServiceRepository.save(factureService);
    }

    public void updateFactureService(FactureService factureService) {
        factureServiceRepository.update(factureService);
    }

    public void deleteFactureService(Integer id) {
        factureServiceRepository.delete(id);
    }

    public FactureService getFactureServiceByServiceId(Integer serviceId) {
        return factureServiceRepository.findByServiceId(serviceId);
    }

    public FactureService registerFactureService(FactureService factureService) {
        factureServiceRepository.save(factureService);
        return factureService;
    }
}




