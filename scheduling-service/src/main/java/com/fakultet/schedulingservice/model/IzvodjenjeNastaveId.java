package com.fakultet.schedulingservice.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class IzvodjenjeNastaveId implements Serializable {
    private Integer predmetId;
    private Integer semestarId;
    private Integer profesorId;
}
