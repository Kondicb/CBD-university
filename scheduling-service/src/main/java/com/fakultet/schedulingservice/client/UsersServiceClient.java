package com.fakultet.schedulingservice.client;

import com.fakultet.schedulingservice.dto.ProfesorInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "users-service")
public interface UsersServiceClient {

    @GetMapping("/api/profesori/{id}")
    ProfesorInfo getProfesorById(@PathVariable("id") Integer id);

    @GetMapping("/api/sluska/predmet/{predmetId}/broj-studenata")
    Long getBrojUpisanihStudenata(@PathVariable("predmetId") Integer predmetId);
}
