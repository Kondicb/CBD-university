package com.fakultet.examsservice.client;

import com.fakultet.examsservice.dto.TerminInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "scheduling-service")
public interface SchedulingServiceClient {

    @GetMapping("/api/termini/{id}")
    TerminInfo getTerminById(@PathVariable("id") Integer id);
}
