package com.example.microservicepfe.services;

import com.example.microservicepfe.dao.PackRepository;
import com.example.microservicepfe.models.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackServiceImpl implements PackService {

    private final PackRepository packRepository;

    @Autowired
    public PackServiceImpl(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    @Override
    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }
}
