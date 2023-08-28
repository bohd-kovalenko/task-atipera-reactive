package com.atipera.testtaskreactive.services.impl;


import com.atipera.testtaskreactive.clients.GitHubRepoClient;
import com.atipera.testtaskreactive.mappers.BranchesMapper;
import com.atipera.testtaskreactive.models.GitHubRepositoryBranch;
import com.atipera.testtaskreactive.payload.responses.BranchResponse;
import com.atipera.testtaskreactive.services.RepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepoServiceImpl implements RepoService {
    private final GitHubRepoClient client;

    @Override
    public Flux<GitHubRepositoryBranch> extractAllRepoBranchesByNameAndUsername(String repoName, String username) {
        Flux<BranchResponse> branchesResponse = client
                .extractBranchesInfoByRepositoryNameAndUsername(repoName, username);
        log.info(String.format("Performed branches extraction for %s repo", repoName));
        return branchesResponse
                .map(branch -> BranchesMapper.INSTANCE.branchResponseToGitHubRepositoryBranch(branch));
    }
}
