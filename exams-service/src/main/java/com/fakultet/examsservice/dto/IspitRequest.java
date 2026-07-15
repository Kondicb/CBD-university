package com.fakultet.examsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IspitRequest {

    @NotNull(message = "Datum je obavezan")
    private LocalDate datum;

    @NotBlank(message = "Rok je obavezan")
    private String rok;

    @NotNull(message = "predmetId je obavezan")
    private Integer predmetId;

    @NotNull(message = "terminId je obavezan")
    private Integer terminId;
}
