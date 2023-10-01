package com.example.microservicepfe.services;

import com.example.microservicepfe.dao.TypeOccupantRespository;
import com.example.microservicepfe.models.TypeOccupant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupantTypeServiceImpl implements OccupantTypeService {

    private final TypeOccupantRespository occupantTypeRepository;

    @Autowired

    public OccupantTypeServiceImpl(TypeOccupantRespository occupantTypeRepository) {
        this.occupantTypeRepository = occupantTypeRepository;
    }

    @Override
    public List<TypeOccupant> getAllOccupantTypes() {
        return occupantTypeRepository.findAll();
    }
}
