package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.entities.Artwork;
import com.casaculturatungurahua.api.entities.Author;
import com.casaculturatungurahua.api.entities.Favorites;
import com.casaculturatungurahua.api.repository.ArtworkRepository;
import com.casaculturatungurahua.api.repository.AuthorRepository;
import com.casaculturatungurahua.api.repository.FavoritesRepository;
import com.casaculturatungurahua.api.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final AuthorRepository authorRepository;

    private final FavoritesRepository favoritesRepository;


    public ArtworkService(ArtworkRepository artworkRepository, AuthorRepository authorRepository, FavoritesRepository favoritesRepository) {
        this.artworkRepository = artworkRepository;
        this.authorRepository = authorRepository;
        this.favoritesRepository = favoritesRepository;
    }

    public Artwork save(Artwork artwork, MultipartFile image) throws IOException {
        Author author = authorRepository.findById(artwork.getAuthor().getId()).orElseThrow();
        artwork.setAuthor(author);
        if(Objects.nonNull(image))
            saveImage(artwork, image);
        return artworkRepository.save(artwork);
    }

    public Artwork update(String code, Artwork artwork, MultipartFile image) throws IOException {
        Artwork artworkToUpdate = artworkRepository.findById(code).orElseThrow(() -> new RuntimeException("La obra de arte no existe"));
        artworkToUpdate.setCode(artwork.getCode());
        artworkToUpdate.setOther_code(artwork.getOther_code());
        artworkToUpdate.setName(artwork.getName());
        artworkToUpdate.setCenturyYear(artwork.getCenturyYear());
        artworkToUpdate.setDeliveryType(artwork.getDeliveryType());
        artworkToUpdate.setSignatureLocation(artwork.getSignatureLocation());
        artworkToUpdate.setCountry(artwork.getCountry());
        artworkToUpdate.setTechnique(artwork.getTechnique());
        artworkToUpdate.setSupport(artwork.getSupport());
        artworkToUpdate.setConservationState(artwork.getConservationState());
        artworkToUpdate.setIntegrityState(artwork.getIntegrityState());
        artworkToUpdate.setIncomeForm(artwork.getIncomeForm());
        artworkToUpdate.setIncomePrice(artwork.getIncomePrice());
        artworkToUpdate.setIncomeYear(artwork.getIncomeYear());
        artworkToUpdate.setValue(artwork.getValue());
        artworkToUpdate.setPieceHeight(artwork.getPieceHeight());
        artworkToUpdate.setPieceWidth(artwork.getPieceWidth());
        artworkToUpdate.setPieceDeep(artwork.getPieceDeep());
        artworkToUpdate.setGravingHeight(artwork.getGravingHeight());
        artworkToUpdate.setGravingWidth(artwork.getGravingWidth());
        artworkToUpdate.setFrameElementHeight(artwork.getFrameElementHeight());
        artworkToUpdate.setFrameElementWidth(artwork.getFrameElementWidth());
        if (Objects.nonNull(image)){
            saveImage(artworkToUpdate, image);
        }
        artworkToUpdate.setImageWordpressURL(artwork.getImageWordpressURL());
        artworkToUpdate.setObservation(artwork.getObservation());
        artworkToUpdate.setLocation(artwork.getLocation());
        artworkToUpdate.setRecordedBy(artwork.getRecordedBy());
        artworkToUpdate.setReviewedBy(artwork.getReviewedBy());
        artworkToUpdate.setRegisteredDate(artwork.getRegisteredDate());
        artworkToUpdate.setAuthor(artwork.getAuthor());
        return artworkRepository.save(artworkToUpdate);
    }

    private void saveImage(Artwork artwork, MultipartFile image) throws IOException {
        /*String[] extension = Objects.requireNonNull(image.getOriginalFilename()).split("\\.");*/
        String artworkFileName = artwork.getCode()+ ".jpg";
        artwork.setImage(ImageUtils.compressImage(image.getBytes()));
        artwork.setImageURL("/casa-cultura-tungurahua/api/v1/images/"+artworkFileName);
    }

    public Boolean delete(String code) throws IOException {
        if (artworkRepository.existsById(code)) {
            /*String[] name =  artworkRepository.findById(code).get().getImageURL().split("/");
            Path imageStorage = Paths.get("images").toAbsolutePath().normalize().resolve(name[name.length -1]);
            Files.deleteIfExists(imageStorage);*/
            artworkRepository.deleteById(code);
            return true;
        }
        return false;
    }

    public List<Artwork> findAll() {
        return artworkRepository.findAll();
    }

    public Artwork findById(String id) {
        Artwork artwork = artworkRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe la obra de arte"));
        return artwork;
    }

    public List<Artwork> findByKeyword(String keyword){
        return artworkRepository.findAllByNameOrAuthorName(keyword);
    }

    public List<Artwork> findAllFavourites(){
        Favorites fav = favoritesRepository.findById(1L).orElse(new Favorites());
        return fav.getArtworks();
    }

    public List<Artwork> saveFavouritesArtworks(List<Artwork> artworks){
        if(favoritesRepository.existsById(1L)){
            favoritesRepository.deleteById(1L);
        }
        Favorites fav = new Favorites();
        fav.setId(1L);
        Favorites finalFav = fav;
        List<Artwork> artworkList = artworks.stream().map(artwork -> artworkRepository.findById(artwork.getCode()).get()).toList();
        fav.setArtworks(artworkList);
        fav = favoritesRepository.save(fav);
        return fav.getArtworks();
    }
}
