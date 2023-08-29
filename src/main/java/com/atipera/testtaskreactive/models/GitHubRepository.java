package com.atipera.testtaskreactive.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public record GitHubRepository(String repositoryName, String ownerUsername, List<GitHubRepositoryBranch> branches) {
    @JsonCreator
    public GitHubRepository(@JsonProperty("repositoryName") String repositoryName,
                            @JsonProperty("ownerUsername") String ownerUsername,
                            @JsonProperty("branches") List<GitHubRepositoryBranch> branches) {
        this.repositoryName = repositoryName;
        this.ownerUsername = ownerUsername;
        this.branches = branches;
    }
}
