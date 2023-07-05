package com.casaculturatungurahua.api.entities;

import com.casaculturatungurahua.api.util.ConservationState;
import com.casaculturatungurahua.api.util.DeliveryType;
import com.casaculturatungurahua.api.util.IntegrityState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artwork {

    //DATOS TECNICOS DEL CUADRO
    @Id
    private String code;
    private String other_code;
    private String name;
    private String centuryYear;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    private String signatureLocation;
    private String country;
    private String technique;
    private String support;
    @Enumerated(EnumType.STRING)
    private ConservationState conservationState;
    @Enumerated(EnumType.STRING)
    private IntegrityState integrityState;
    private String incomeForm;
    private BigDecimal incomePrice;
    private Integer incomeYear;
    private BigDecimal value;
    //Dimensiones
    private Double pieceHeight;
    private Double pieceWidth;
    private Double pieceDeep;
    private Double gravingHeight;
    private Double gravingWidth;
    private Double frameElementHeight;
    private Double frameElementWidth;
    //OTRA INFORMACION
    private String imageURL;
    private String imageWordpressURL;
    @Column(columnDefinition = "TEXT")
    private String observation;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String location;
    private String recordedBy;
    private String reviewedBy;
    private LocalDate registeredDate;
    @ManyToOne
    private Author author;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinTable(name = "favorites_artworks",
            joinColumns = @JoinColumn(name = "artworks_code"),
            inverseJoinColumns = @JoinColumn(name = "favorites_id"))
    private Favorites favorites;

    @PreRemove
    public void preRemove() throws IOException {
        String[] name =  imageURL.split("/");
        Path imageStorage = Paths.get("images").toAbsolutePath().normalize().resolve(name[name.length -1]);
        Files.deleteIfExists(imageStorage);
    }

}
