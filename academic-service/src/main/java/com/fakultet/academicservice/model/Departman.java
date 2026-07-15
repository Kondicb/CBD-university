package com.fakultet.academicservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departman_id")
    private Integer departmanId;

    @NotBlank(message = "naziv departmana je obavezan")
    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @OneToMany(mappedBy = "departman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Smer> smerovi = new ArrayList<>();
}
