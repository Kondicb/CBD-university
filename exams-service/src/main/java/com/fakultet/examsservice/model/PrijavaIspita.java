package com.fakultet.examsservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "prijavaispita")
@Getter
@Setter
@NoArgsConstructor
public class PrijavaIspita {

    @EmbeddedId
    private PrijavaIspitaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ispitId")
    @JoinColumn(name = "ispit_id")
    private Ispit ispit;

    @Column(name = "datum_prijave", nullable = false)
    private LocalDate datumPrijave = LocalDate.now();


    @Column(name = "ocena")
    private Integer ocena;

    public PrijavaIspita(Integer studentId, Ispit ispit) {
        this.id = new PrijavaIspitaId(studentId, ispit.getIspitId());
        this.ispit = ispit;
    }
}
