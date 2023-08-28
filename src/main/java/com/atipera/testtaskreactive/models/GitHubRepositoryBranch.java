package com.atipera.testtaskreactive.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubRepositoryBranch(String name, String lastCommitSha) {
    @JsonCreator
    public GitHubRepositoryBranch(@JsonProperty("name") String name,
                                  @JsonProperty("lastCommitSha") String lastCommitSha) {
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }
}
