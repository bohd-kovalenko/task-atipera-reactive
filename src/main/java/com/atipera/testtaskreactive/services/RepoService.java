package com.atipera.testtaskreactive.services;


import com.atipera.testtaskreactive.models.GitHubRepositoryBranch;
import reactor.core.publisher.Flux;

public interface RepoService {
    Flux<GitHubRepositoryBranch> extractAllRepoBranchesByNameAndUsername(String repoName, String username);
}
