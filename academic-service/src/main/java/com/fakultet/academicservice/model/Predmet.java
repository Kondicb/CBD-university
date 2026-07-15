package com.fakultet.academicservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "predmet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "predmet_id")
    private Integer predmetId;

    @NotBlank(message = "naziv predmeta je obavezan")
    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @Min(value = 1, message = "ESPB mora biti veci od 0")
    @Column(name = "espb", nullable = false)
    private Integer espb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smer_id", nullable = false)
    private Smer smer;

    @Min(value = 1, message = "godina studija mora biti veca od 0")
    @Column(name = "godina", nullable = false)
    private Integer godina = 1;
}
