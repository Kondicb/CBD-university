package com.fakultet.apigateway.client;

import com.fakultet.apigateway.dto.KorisnikInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UsersServiceClient {

    private final WebClient webClient;

    public UsersServiceClient(WebClient.Builder loadBalancedWebClientBuilder) {
        this.webClient = loadBalancedWebClientBuilder.baseUrl("http://users-service").build();
    }

    public Mono<KorisnikInfo> getByEmail(String email) {
        return webClient.get()
                .uri("/api/korisnici/email/{email}", email)
                .retrieve()
                .onStatus(status -> status.value() == 404, resp -> Mono.empty())
                .bodyToMono(KorisnikInfo.class)
                .onErrorResume(ex -> Mono.empty());
    }
}
