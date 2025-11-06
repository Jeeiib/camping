package com.cda.camping.service;

import com.cda.camping.model.Type;
import com.cda.camping.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type getType(Integer id) {
        return typeRepository.findById(id);
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public void createType(Type type) {
        typeRepository.save(type);
    }

    public void updateType(Type type) {
        typeRepository.update(type);
    }

    public void deleteType(Integer id) {
        typeRepository.delete(id);
    }

    public Type findByLabel(String label) {
        return typeRepository.findByLabel(label);
    }

    public Type registerType(Type type) {
        typeRepository.save(type);
        return type;
    }
}

