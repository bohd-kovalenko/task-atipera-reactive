package com.atipera.testtaskreactive.payload.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CommitResponse(String sha) {
    @JsonCreator
    public CommitResponse(@JsonProperty("sha") String sha) {
        this.sha = sha;
    }
}
