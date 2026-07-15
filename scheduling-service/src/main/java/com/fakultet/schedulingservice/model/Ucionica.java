package com.fakultet.schedulingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ucionica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ucionica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ucionica_id")
    private Integer ucionicaId;

    @NotBlank(message = "Oznaka ucionice je obavezna")
    @Column(name = "oznaka", nullable = false, length = 20)
    private String oznaka;

    @Min(value = 1, message = "Kapacitet mora biti veci od 0")
    @Column(name = "kapacitet", nullable = false)
    private Integer kapacitet;
}
