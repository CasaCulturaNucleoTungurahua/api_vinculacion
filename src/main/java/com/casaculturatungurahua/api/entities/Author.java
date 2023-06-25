package com.casaculturatungurahua.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    private String fullName;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String bibliography;
    @JsonIgnore
    @OneToMany( mappedBy = "author")
    private List<Artwork> artworks = new ArrayList<>();
}

