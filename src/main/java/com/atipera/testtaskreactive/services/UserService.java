package com.atipera.testtaskreactive.services;


import com.atipera.testtaskreactive.models.GitHubRepository;
import reactor.core.publisher.Flux;

public interface UserService {
    Flux<GitHubRepository> extractAllRepositoriesByUsername(String username);
}
