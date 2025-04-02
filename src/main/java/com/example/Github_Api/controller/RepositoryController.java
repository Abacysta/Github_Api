package com.example.Github_Api.controller;

import com.example.Github_Api.client.GithubClient;
import com.example.Github_Api.model.GithubRepoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/github")
public class RepositoryController {
    private GithubClient githubClient;

    public RepositoryController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping("/{user}")
    public ResponseEntity<?> getUserRepositories(@PathVariable String user) {
        List<GithubRepoResponse> repos = githubClient.getRepositories(user).stream()
                .map(repo -> new GithubRepoResponse(
                        repo.getName(),
                        repo.getOwner().getLogin(),
                        githubClient.getBranches(user, repo.getName())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(repos);
    }
}
