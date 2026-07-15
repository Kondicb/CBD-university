package com.fakultet.schedulingservice.dto;

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
public class ProfesorInfo {
    private Integer profesorId;
    private String prezime;
    private String titula;
    private String kabinet;
    private Integer departmanId;
}
