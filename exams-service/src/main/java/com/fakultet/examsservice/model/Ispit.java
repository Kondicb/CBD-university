package com.fakultet.examsservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ispit")
@Getter
@Setter
@NoArgsConstructor
public class Ispit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ispit_id")
    private Integer ispitId;

    @NotNull(message = "Datum ispita je obavezan")
    @Column(name = "datum", nullable = false)
    private LocalDate datum;


    @NotBlank(message = "Rok je obavezan")
    @Column(name = "rok", nullable = false, length = 50)
    private String rok;

    @Column(name = "predmet_id", nullable = false)
    private Integer predmetId;


    @Column(name = "termin_id", nullable = false)
    private Integer terminId;
}
