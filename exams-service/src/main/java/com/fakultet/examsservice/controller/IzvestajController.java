package com.fakultet.examsservice.controller;

import com.fakultet.examsservice.dto.KonacnaOcenaResponse;
import com.fakultet.examsservice.service.IzvestajService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/izvestaji")
public class IzvestajController {

    private final IzvestajService izvestajService;

    public IzvestajController(IzvestajService izvestajService) {
        this.izvestajService = izvestajService;
    }

    @GetMapping("/student/{studentId}/predmet/{predmetId}")
    public KonacnaOcenaResponse getKonacnaOcena(@PathVariable Integer studentId,
                                                 @PathVariable Integer predmetId) {
        return izvestajService.getKonacnaOcena(studentId, predmetId);
    }
}
