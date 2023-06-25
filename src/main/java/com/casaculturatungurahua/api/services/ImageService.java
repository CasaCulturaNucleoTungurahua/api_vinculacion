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
        Path imageStorage = Paths.get("images").toAbsolutePath().normalize().resolve(imageName);
        return Files.readAllBytes(new File(imageStorage.toUri()).toPath());
    }
}
