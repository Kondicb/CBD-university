package com.fakultet.schedulingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * scheduling-service - ucionice, termini, semestri i izvodjenje nastave
 * (dodela profesora predmetu u datom semestru/terminu).
 *
 * Koristi Feign da pozove:
 *  - academic-service: provera da predmet postoji
 *  - users-service: provera da profesor postoji, i broj upisanih
 *    studenata na predmet (za proveru kapaciteta ucionice)
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SchedulingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingServiceApplication.class, args);
    }
}
