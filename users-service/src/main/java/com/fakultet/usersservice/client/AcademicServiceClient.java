package com.fakultet.usersservice.client;

import com.fakultet.usersservice.dto.PredmetInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "academic-service")
public interface AcademicServiceClient {

    @GetMapping("/api/predmeti/{id}")
    PredmetInfo getPredmetById(@PathVariable("id") Integer id);
}
