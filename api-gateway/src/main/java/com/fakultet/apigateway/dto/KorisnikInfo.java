package com.fakultet.apigateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KorisnikInfo {
    private Integer korisnikId;
    private String ime;
    private String email;
    private String password;
    private String role;
}
