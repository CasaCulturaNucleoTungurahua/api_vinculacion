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
        if(imageName == null) return null;
        int lastDotIndex = imageName.lastIndexOf(".");
        String code = imageName.substring(0, lastDotIndex);
        Artwork artwork = artworkRepository.findById(code).orElseThrow(() -> new RuntimeException("La obra de arte no existe " + code));
        if(artwork.getImage() == null) return null;
        return ImageUtils.decompressImage(artwork.getImage());
    }
}
