package com.fakultet.schedulingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TerminRequest {

    @NotNull(message = "ucionicaId je obavezan")
    private Integer ucionicaId;

    @NotBlank(message = "danUNedelji je obavezan")
    private String danUNedelji;

    @NotNull(message = "vremeOd je obavezno")
    private LocalTime vremeOd;

    @NotNull(message = "vremeDo je obavezno")
    private LocalTime vremeDo;
}
