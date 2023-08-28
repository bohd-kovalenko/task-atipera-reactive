package com.atipera.testtaskreactive.payload.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RepositoryOwnerResponse(String login) {
    @JsonCreator
    public RepositoryOwnerResponse(@JsonProperty("login") String login) {
        this.login = login;
    }
}
