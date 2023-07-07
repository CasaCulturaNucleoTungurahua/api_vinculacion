package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.io.IOException;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] downloadImage(@PathVariable String imageName) throws IOException {
        return imageService.downloadImage(imageName);
    }
}
