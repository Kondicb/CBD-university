package com.fakultet.schedulingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "termin")
@Getter
@Setter
@NoArgsConstructor
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termin_id")
    private Integer terminId;

    @NotBlank(message = "Dan u nedelji je obavezan")
    @Column(name = "dan_u_nedelji", nullable = false, length = 20)
    private String danUNedelji;

    @Column(name = "vreme_od", nullable = false)
    private LocalTime vremeOd;

    @Column(name = "vreme_do", nullable = false)
    private LocalTime vremeDo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ucionica_id", nullable = false)
    private Ucionica ucionica;
}
