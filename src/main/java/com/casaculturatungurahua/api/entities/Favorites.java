package com.casaculturatungurahua.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {
    @Id
    private Long id;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Artwork> artworks = new ArrayList<>();

}
