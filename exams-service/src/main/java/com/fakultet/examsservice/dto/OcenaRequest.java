package com.fakultet.examsservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcenaRequest {

    @NotNull(message = "Ocena je obavezna")
    @Min(value = 5, message = "Ocena mora biti izmedju 5 i 10")
    @Max(value = 10, message = "Ocena mora biti izmedju 5 i 10")
    private Integer ocena;
}
