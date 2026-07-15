package com.fakultet.schedulingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "semestar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Semestar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semestar_id")
    private Integer semestarId;

    @Column(name = "godina", nullable = false)
    private Integer godina;

    // ZIMSKI, LETNJI
    @NotBlank(message = "Sezona je obavezna")
    @Column(name = "sezona", nullable = false, length = 20)
    private String sezona;
}
