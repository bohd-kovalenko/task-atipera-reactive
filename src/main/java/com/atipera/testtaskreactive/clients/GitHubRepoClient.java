package com.atipera.testtaskreactive.clients;

import com.atipera.testtaskreactive.payload.responses.BranchResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface GitHubRepoClient {
    @GetExchange(value = "/{username}/{repositoryName}/branches")
    Flux<BranchResponse> extractBranchesInfoByRepositoryNameAndUsername(@PathVariable("repositoryName") String repositoryName,
                                                                        @PathVariable("username") String username);
}
