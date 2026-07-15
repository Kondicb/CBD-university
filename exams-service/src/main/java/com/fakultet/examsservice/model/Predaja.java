package com.fakultet.examsservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "predaja")
@Getter
@Setter
@NoArgsConstructor
public class Predaja {

    @EmbeddedId
    private PredajaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("zadatakId")
    @JoinColumn(name = "zadatak_id")
    private DomaciZadatak zadatak;

    @Column(name = "datum_predaje", nullable = false)
    private LocalDate datumPredaje = LocalDate.now();

    @Column(name = "bodovi")
    private Integer bodovi;

    public Predaja(Integer studentId, DomaciZadatak zadatak) {
        this.id = new PredajaId(studentId, zadatak.getZadatakId());
        this.zadatak = zadatak;
    }
}
