package com.fakultet.examsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ZadatakRequest {

    @NotBlank(message = "Opis je obavezan")
    private String opis;

    @NotNull(message = "Rok je obavezan")
    private LocalDate rok;

    @NotNull(message = "predmetId je obavezan")
    private Integer predmetId;
}
