package com.fakultet.schedulingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "izvodjenje_nastave")
@Getter
@Setter
@NoArgsConstructor
public class IzvodjenjeNastave {

    @EmbeddedId
    private IzvodjenjeNastaveId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("semestarId")
    @JoinColumn(name = "semestar_id")
    private Semestar semestar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "termin_id", nullable = false)
    private Termin termin;

    public IzvodjenjeNastave(Integer predmetId, Semestar semestar, Integer profesorId, Termin termin) {
        this.id = new IzvodjenjeNastaveId(predmetId, semestar.getSemestarId(), profesorId);
        this.semestar = semestar;
        this.termin = termin;
    }
}
