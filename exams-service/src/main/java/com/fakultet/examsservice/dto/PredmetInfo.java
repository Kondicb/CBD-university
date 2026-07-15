package com.fakultet.examsservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredmetInfo {
    private Integer predmetId;
    private String naziv;
    private Integer espb;
    private Integer smerId;
    private Integer godina;
}
