package com.fakultet.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpisaniPredmetResponse {
    private Integer predmetId;
    private String nazivPredmeta;
    private Integer espb;
    private String datumUpisa;
}
