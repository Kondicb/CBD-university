package com.fakultet.examsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service")
public interface UsersServiceClient {

    @GetMapping("/api/studenti/{id}")
    Object getStudentById(@PathVariable("id") Integer id);

    @GetMapping("/api/sluska/{studentId}/upisan/{predmetId}")
    Boolean jeUpisan(@PathVariable("studentId") Integer studentId,
                      @PathVariable("predmetId") Integer predmetId);
}
