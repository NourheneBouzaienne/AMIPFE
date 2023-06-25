package com.example.microservicepfe.services;

import com.example.microservicepfe.models.Sinistre;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SinistreService {
    //Sinistre createSinistre(Sinistre sinistre, MultipartFile constatFile, List<MultipartFile> sinistrePhotos) throws IOException;

    Sinistre createSinistre(Sinistre sinistre, List<MultipartFile> constatPhotos, List<MultipartFile> sinistrePhotos) throws IOException;







}
