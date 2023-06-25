package com.example.microservicepfe.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.microservicepfe.dao.ConstatRepository;
import com.example.microservicepfe.dao.PhotoSinistreRepository;
import com.example.microservicepfe.dao.SinistreRepository;
import com.example.microservicepfe.models.Constat;
import com.example.microservicepfe.models.PhotoSinistre;
import com.example.microservicepfe.models.Sinistre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SinistreServiceImpl implements SinistreService {

    @Autowired
    SinistreRepository sinistreRepository;

    @Autowired
    PhotoSinistreRepository photoRepository;

    @Autowired
    ConstatRepository constatRepository;

    @Override
    public Sinistre createSinistre(Sinistre sinistre,  List<MultipartFile> constatPhotos, List<MultipartFile> sinistrePhotos) throws IOException {
        // Enregistrer le sinistre dans la base de données
        Sinistre savedSinistre = sinistreRepository.save(sinistre);

        // Enregistrer le constat (photo) dans Cloudinary
        //String constatUrl = uploadToCloudinary(constatFile);
        //PhotoSinistre constatPhoto = new PhotoSinistre(constatUrl, savedSinistre);
        //photoRepository.save(constatPhoto);

        // Enregistrer Constat du sinistre dans Cloudinary
        List<Constat> constatList = new ArrayList<>();
        for (MultipartFile constatFile : constatPhotos) {
            String constatUrl = uploadToCloudinary(constatFile);
            Constat constatPhoto = new Constat(constatUrl, savedSinistre);
            constatList.add(constatPhoto);
        }
        constatRepository.saveAll(constatList);

        // Mettre à jour la liste de CONSTATS du sinistre
        savedSinistre.setConstat(constatList);
        sinistreRepository.save(savedSinistre);

        // Enregistrer les photos du sinistre dans Cloudinary
        List<PhotoSinistre> sinistrePhotoList = new ArrayList<>();
        for (MultipartFile sinistrePhotoFile : sinistrePhotos) {
            String sinistrePhotoUrl = uploadToCloudinary(sinistrePhotoFile);
            PhotoSinistre sinistrePhoto = new PhotoSinistre(sinistrePhotoUrl, savedSinistre);
            sinistrePhotoList.add(sinistrePhoto);
        }
        photoRepository.saveAll(sinistrePhotoList);

        // Mettre à jour la liste de photos du sinistre
        savedSinistre.setPhotos(sinistrePhotoList);
        sinistreRepository.save(savedSinistre);

        return savedSinistre;
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dehenf30n");
        config.put("api_key", "498693434755289");
        config.put("api_secret", "rUSalYnZ5SZ_mNIH7loheDxowb4");

        Cloudinary cloudinary = new Cloudinary(config);
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        return uploadResult.get("secure_url").toString();
    }

}
