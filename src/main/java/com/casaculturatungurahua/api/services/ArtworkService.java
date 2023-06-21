package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.entities.Artwork;
import com.casaculturatungurahua.api.repository.ArtworkRepository;
import com.casaculturatungurahua.api.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final AuthorRepository authorRepository;


    public ArtworkService(ArtworkRepository artworkRepository, AuthorRepository authorRepository) {
        this.artworkRepository = artworkRepository;
        this.authorRepository = authorRepository;
    }

    public Artwork save(Artwork artwork){
        return artworkRepository.save(artwork);
    }

    public Artwork update(Long id,Artwork artwork){
        Artwork artworkToUpdate = artworkRepository.findById(id).orElseThrow(()-> new RuntimeException("La obra de arte no existe"));
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
        artworkToUpdate.setImageURL(artwork.getImageURL());
        artworkToUpdate.setObservation(artwork.getObservation());
        artworkToUpdate.setLocation(artwork.getLocation());
        artworkToUpdate.setRecordedBy(artwork.getRecordedBy());
        artworkToUpdate.setReviewedBy(artwork.getReviewedBy());
        artworkToUpdate.setAuthor(artwork.getAuthor());
        return artworkRepository.save(artworkToUpdate);
    }

    public Boolean delete(Long id){
        if(artworkRepository.existsById(id)){
            artworkRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Artwork> findAll(){
        return artworkRepository.findAll();
    }

    public Artwork findById(Long id){
        return artworkRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe la obra de arte"));
    }
}
