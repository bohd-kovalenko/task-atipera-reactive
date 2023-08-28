package com.atipera.testtaskreactive.payload.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RepositoryResponse(String name, RepositoryOwnerResponse repositoryOwner, Boolean isFork) {
    @JsonCreator
    public RepositoryResponse(@JsonProperty("name") String name,
                              @JsonProperty("owner") RepositoryOwnerResponse repositoryOwner,
                              @JsonProperty("fork") Boolean isFork) {
        this.name = name;
        this.repositoryOwner = repositoryOwner;
        this.isFork = isFork;
    }
}
