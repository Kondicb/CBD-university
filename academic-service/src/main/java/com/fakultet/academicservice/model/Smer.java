package com.fakultet.academicservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "smer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Smer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smer_id")
    private Integer smerId;

    @NotBlank(message = "naziv smera je obavezan")
    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    // OSNOVNE, MASTER, DOKTORSKE
    @NotBlank(message = "nivo studija je obavezan")
    @Column(name = "nivo", nullable = false, length = 20)
    private String nivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departman_id", nullable = false)
    private Departman departman;

    @OneToMany(mappedBy = "smer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Predmet> predmeti = new ArrayList<>();
}
