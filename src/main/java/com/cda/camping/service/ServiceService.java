package com.cda.camping.service;

import com.cda.camping.model.Services;
import com.cda.camping.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public Services getService(Integer id) {
        return serviceRepository.findById(id);
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public void createService(Services service) {
        serviceRepository.save(service);
    }

    public void updateService(Services service) {
        serviceRepository.update(service);
    }

    public void deleteService(Integer id) {
        serviceRepository.delete(id);
    }

    public Services findByLabel(String label) {
        return serviceRepository.findByLabel(label);
    }

    public Services registerService(Services service) {
        serviceRepository.save(service);
        return service;
    }
}
