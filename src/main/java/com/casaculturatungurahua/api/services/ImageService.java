package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.entities.Artwork;
import com.casaculturatungurahua.api.repository.ArtworkRepository;
import com.casaculturatungurahua.api.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageService {

    private final ArtworkRepository artworkRepository;

    public ImageService(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    public byte[] downloadImage(String imageName) {
        String code = imageName.split("\\.")[0];
        Artwork artwork = artworkRepository.findById(code).orElseThrow(() -> new RuntimeException("La obra de arte no existe"));
        return ImageUtils.decompressImage(artwork.getImage());
    }
}
