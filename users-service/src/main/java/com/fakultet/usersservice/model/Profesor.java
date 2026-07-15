package com.fakultet.usersservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
public class Profesor {

    @Id
    @Column(name = "profesor_id")
    private Integer profesorId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "profesor_id")
    private Korisnik korisnik;

    @NotBlank(message = "Prezime je obavezno")
    @Column(name = "prezime", nullable = false, length = 50)
    private String prezime;

    @Column(name = "titula", length = 50)
    private String titula;

    @Column(name = "kabinet", length = 20)
    private String kabinet;

    @Column(name = "departman_id", nullable = false)
    private Integer departmanId;
}
