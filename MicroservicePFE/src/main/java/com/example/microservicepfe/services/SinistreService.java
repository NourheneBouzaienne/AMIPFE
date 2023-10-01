package com.example.microservicepfe.services;

import com.example.microservicepfe.models.Client;
import com.example.microservicepfe.models.Sinistre;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SinistreService {
    //Sinistre createSinistre(Sinistre sinistre, MultipartFile constatFile, List<MultipartFile> sinistrePhotos) throws IOException;

    Sinistre createSinistre(Sinistre sinistre, List<MultipartFile> constatPhotos, List<MultipartFile> sinistrePhotos) throws IOException;



    List<Sinistre> getAllSinistres();
    Optional<Sinistre> getSinistreById(Long id);
    Sinistre updateSinistre(Sinistre client);
    void deleteSinistreById(Long id);





}
