package com.fakultet.academicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanGodineResponse {
    private Integer godina;
    private List<PredmetResponse> predmeti;
    private Integer ukupnoEspb;
}
