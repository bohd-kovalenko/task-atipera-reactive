package com.atipera.testtaskreactive.configs;

import com.atipera.testtaskreactive.clients.GitHubRepoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Bean
    public GitHubRepoClient gitHubRepoClient(WebClient.Builder webClientBuilder) {
        WebClientAdapter webClientAdapter = WebClientAdapter.forClient(webClientBuilder
                .baseUrl("https://api.github.com/repos")
                .build());
        return HttpServiceProxyFactory
                .builder()
                .clientAdapter(webClientAdapter)
                .build()
                .createClient(GitHubRepoClient.class);
    }
}
