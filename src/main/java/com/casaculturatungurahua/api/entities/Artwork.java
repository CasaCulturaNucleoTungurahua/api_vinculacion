package com.casaculturatungurahua.api.entities;

import com.casaculturatungurahua.api.util.ConservationState;
import com.casaculturatungurahua.api.util.DeliveryType;
import com.casaculturatungurahua.api.util.IntegrityState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private String observation;
    private String location;
    private String recordedBy;
    private String reviewedBy;
    private LocalDate registeredDate;
    @ManyToOne
    private Author author;

}
