package com.fakultet.academicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredmetResponse {
    private Integer predmetId;
    private String naziv;
    private Integer espb;
    private Integer smerId;
    private Integer godina;
}
