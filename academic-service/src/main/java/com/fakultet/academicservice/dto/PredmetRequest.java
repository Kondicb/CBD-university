package com.fakultet.academicservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredmetRequest {

    @NotBlank(message = "naziv predmeta je obavezan")
    private String naziv;

    @NotNull(message = "ESPB je obavezan")
    @Min(value = 1, message = "ESPB mora biti veci od 0")
    private Integer espb;

    @NotNull(message = "smerId je obavezan")
    private Integer smerId;

    @NotNull(message = "godina studija je obavezna")
    @Min(value = 1, message = "Godina studija mora biti veca od 0")
    private Integer godina;
}
