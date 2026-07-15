package com.fakultet.academicservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "materijal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Materijal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materijal_id")
    private Integer materijalId;

    @NotBlank(message = "naziv materijala je obavezan")
    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    // PREZENTACIJA, SKRIPTA, ZADACI, VIDEO
    @NotBlank(message = "tip materijala je obavezan")
    @Column(name = "tip", nullable = false, length = 20)
    private String tip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @Column(name = "datum_objave", nullable = false)
    private LocalDate datumObjave = LocalDate.now();
}
