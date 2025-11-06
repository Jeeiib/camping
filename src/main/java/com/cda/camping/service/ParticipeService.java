package com.cda.camping.service;

import com.cda.camping.model.Participe;
import com.cda.camping.model.ParticipeId;
import com.cda.camping.repository.ParticipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipeService {

    @Autowired
    private ParticipeRepository participeRepository;

    public Participe getParticipe(ParticipeId id) {
        return participeRepository.findById(id);
    }

    public List<Participe> getAllParticipes() {
        return participeRepository.findAll();
    }

    public void createParticipe(Participe participe) {
        participeRepository.save(participe);
    }

    public void updateParticipe(Participe participe) {
        participeRepository.update(participe);
    }

    public void deleteParticipe(ParticipeId id) {
        participeRepository.delete(id);
    }

    public List<Participe> getParticipesByClient(Integer clientId) {
        return participeRepository.findByClient(clientId);
    }

    public Participe registerParticipe(Participe participe) {
        participeRepository.save(participe);
        return participe;
    }
}




