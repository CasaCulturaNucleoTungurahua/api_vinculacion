package com.casaculturatungurahua.api.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    public byte[] downloadImage(String imageName) throws IOException {
        Path imageFolder = Paths.get("home").toAbsolutePath().normalize();
        if(!Files.exists(imageFolder)){
            try {
                Files.createDirectories(imageFolder);
                System.out.println("Directorio creado");
            } catch (Exception e) {
                throw new RuntimeException("Error al crear el directorio para guardar la imagen");
            }
        }else{
            System.out.println("El directorio ya existe");
        }
        Path imageStorage = imageFolder.resolve(imageName);
        return Files.readAllBytes(new File(imageStorage.toUri()).toPath());
    }
}
