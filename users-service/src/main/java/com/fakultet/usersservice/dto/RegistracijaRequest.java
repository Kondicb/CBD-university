package com.fakultet.usersservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistracijaRequest {

    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    @NotBlank(message = "Email je obavezan")
    @Email(message = "Email nije validnog formata")
    private String email;

    @NotBlank(message = "Lozinka je obavezna")
    private String password;

    @NotBlank(message = "Rola je obavezna")
    @Pattern(regexp = "STUDENT|PROFESOR|ADMIN", message = "Rola mora biti STUDENT, PROFESOR ili ADMIN")
    private String role;

    private Integer godinaUpisa;
    private Integer smerId;

    private String titula;
    private String kabinet;
    private Integer departmanId;
}
