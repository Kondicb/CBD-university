package com.fakultet.schedulingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RasporedStavkaResponse {
    private String nazivPredmeta;
    private String profesorPrezime;
    private String danUNedelji;
    private String vremeOd;
    private String vremeDo;
    private String ucionicaOznaka;
}
