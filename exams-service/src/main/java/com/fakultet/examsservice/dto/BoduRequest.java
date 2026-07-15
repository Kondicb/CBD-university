package com.fakultet.examsservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoduRequest {

    @NotNull(message = "bodovi su obavezni")
    @Min(value = 0, message = "bodovi ne mogu biti negativni")
    private Integer bodovi;
}
