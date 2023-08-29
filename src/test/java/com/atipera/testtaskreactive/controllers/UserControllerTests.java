package com.atipera.testtaskreactive.controllers;

import com.atipera.testtaskreactive.models.GitHubRepository;
import com.atipera.testtaskreactive.models.GitHubRepositoryBranch;
import com.atipera.testtaskreactive.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UserService userService;
    private final String testUsername = "test-username";

    @Test
    public void testHappyPath() {
        GitHubRepositoryBranch testBranch = new GitHubRepositoryBranch("testBranch", "testSha");
        GitHubRepository gitHubRepository = new GitHubRepository("testRepository",
                testUsername,
                List.of(testBranch));
        Flux<GitHubRepository> expectedResult = Flux.just(gitHubRepository);


        when(userService.extractAllRepositoriesByUsername(testUsername))
                .thenReturn(expectedResult);

        webTestClient.get()
                .uri("/github/users/" + testUsername + "/repositories")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GitHubRepository.class)
                .hasSize(1)
                .value(value -> value.equals(expectedResult));
    }

    @Test
    public void testUserWithoutRepos() {
        Flux<GitHubRepository> expectedResult = Flux.just();

        when(userService.extractAllRepositoriesByUsername(testUsername))
                .thenReturn(expectedResult);

        webTestClient.get()
                .uri("/github/users/" + testUsername + "/repositories")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GitHubRepository.class)
                .hasSize(0)
                .value(value -> value.equals(expectedResult));
    }
}

