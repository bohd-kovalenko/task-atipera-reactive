package com.atipera.testtaskreactive.mappers;


import com.atipera.testtaskreactive.models.GitHubRepositoryBranch;
import com.atipera.testtaskreactive.payload.responses.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchesMapper {
    BranchesMapper INSTANCE = Mappers.getMapper(BranchesMapper.class);

    default GitHubRepositoryBranch branchResponseToGitHubRepositoryBranch(BranchResponse branchResponse) {
        return new GitHubRepositoryBranch(
                branchResponse.name(),
                branchResponse.lastCommit().sha()
        );
    }

}
