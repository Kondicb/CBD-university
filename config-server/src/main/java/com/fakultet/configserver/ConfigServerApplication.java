package com.fakultet.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * config-server - centralizovana konfiguracija za sve mikroservise.
 *
 * Koristi "native" profil (spring.profiles.active=native u
 * application.properties), sto znaci da cita konfiguracione fajlove
 * sa lokalnog fajl-sistema/classpath-a (folder config-repo), umesto
 * sa udaljenog Git repozitorijuma. Ovo je namerno pojednostavljeno za
 * potrebe fakultetskog projekta - u produkciji bi se obicno koristio
 * pravi Git repo (spring.cloud.config.server.git.uri=...).
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
