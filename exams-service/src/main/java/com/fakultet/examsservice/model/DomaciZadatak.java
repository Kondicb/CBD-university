package com.fakultet.examsservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "domacizadatak")
@Getter
@Setter
@NoArgsConstructor
public class DomaciZadatak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zadatak_id")
    private Integer zadatakId;

    @NotBlank(message = "Opis zadatka je obavezan")
    @Column(name = "opis", nullable = false, columnDefinition = "TEXT")
    private String opis;

    @NotNull(message = "Rok predaje je obavezan")
    @Column(name = "rok", nullable = false)
    private LocalDate rok;

    @Column(name = "predmet_id", nullable = false)
    private Integer predmetId;
}
