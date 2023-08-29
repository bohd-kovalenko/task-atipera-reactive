package com.atipera.testtaskreactive.services.impl;

import com.atipera.testtaskreactive.exceptions.UserNotFoundException;
import com.atipera.testtaskreactive.models.GitHubRepository;
import com.atipera.testtaskreactive.payload.responses.RepositoryResponse;
import com.atipera.testtaskreactive.services.RepoService;
import com.atipera.testtaskreactive.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final WebClient client;
    private final RepoService repoService;

    public UserServiceImpl(WebClient.Builder builder, RepoService repoService) {
        this.client = builder.baseUrl("https://api.github.com/users").build();
        this.repoService = repoService;
    }

    @Override
    public Flux<GitHubRepository> extractAllRepositoriesByUsername(String username) {
        return client
                .get()
                .uri(String.format("/%s/repos", username))
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND),
                        response -> Mono.error(new UserNotFoundException("No such user on GitHub")))
                .bodyToFlux(RepositoryResponse.class)
                .filter(repository -> !repository.isFork())
                .flatMap(repository ->
                        repoService
                                .extractAllRepoBranchesByNameAndUsername(repository.name(), username)
                                .collectList()
                                .map(branches -> new GitHubRepository(
                                        repository.name(),
                                        repository.repositoryOwner().login(),
                                        branches
                                ))
                );
    }
}