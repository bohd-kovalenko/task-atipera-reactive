package com.atipera.testtaskreactive.payload.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BranchResponse(String name, CommitResponse lastCommit) {
    @JsonCreator
    public BranchResponse(@JsonProperty("name") String name,
                          @JsonProperty("commit") CommitResponse lastCommit) {
        this.name = name;
        this.lastCommit = lastCommit;
    }
}
