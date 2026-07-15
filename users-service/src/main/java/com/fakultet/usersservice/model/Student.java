package com.fakultet.usersservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "student_id")
    private Korisnik korisnik;

    @NotBlank(message = "Prezime je obavezno")
    @Column(name = "prezime", nullable = false, length = 50)
    private String prezime;

    @Min(value = 1900, message = "Godina upisa nije validna")
    @Column(name = "godina_upisa", nullable = false)
    private Integer godinaUpisa;

    @Column(name = "smer_id", nullable = false)
    private Integer smerId;
}
