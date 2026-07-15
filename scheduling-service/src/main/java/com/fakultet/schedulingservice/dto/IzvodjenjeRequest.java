package com.fakultet.schedulingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IzvodjenjeRequest {

    @NotNull(message = "predmetId je obavezan")
    private Integer predmetId;

    @NotNull(message = "semestarId je obavezan")
    private Integer semestarId;

    @NotNull(message = "profesorId je obavezan")
    private Integer profesorId;

    @NotNull(message = "terminId je obavezan")
    private Integer terminId;
}
