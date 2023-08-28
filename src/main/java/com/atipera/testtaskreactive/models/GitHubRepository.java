package com.atipera.testtaskreactive.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import reactor.core.publisher.Flux;

public record GitHubRepository(String repositoryName, String ownerUsername, Flux<GitHubRepositoryBranch> branches) {
    @JsonCreator
    public GitHubRepository(@JsonProperty("repositoryName") String repositoryName,
                            @JsonProperty("ownerUsername") String ownerUsername,
                            @JsonProperty("branches") Flux<GitHubRepositoryBranch> branches) {
        this.repositoryName = repositoryName;
        this.ownerUsername = ownerUsername;
        this.branches = branches;
    }
}
