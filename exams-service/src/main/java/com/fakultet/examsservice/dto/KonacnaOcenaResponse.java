package com.fakultet.examsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KonacnaOcenaResponse {
    private Integer studentId;
    private Integer predmetId;
    private Integer ocenaIspita;       // null ako jos nije izasao/upisan
    private Integer ukupnoBodovaZadaci;
    private String napomena;
}
