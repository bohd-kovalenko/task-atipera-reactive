package com.atipera.testtaskreactive.controllers;

import com.atipera.testtaskreactive.models.GitHubRepository;
import com.atipera.testtaskreactive.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/github/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{username}/repositories", produces = "application/json")
    public ResponseEntity<Flux<GitHubRepository>> extractAllUsersRepositories(@PathVariable("username") String username) {
        log.info("GET method on /github/users/{username}/repositories");
        Flux<GitHubRepository> responseBody = userService.extractAllRepositoriesByUsername(username);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}