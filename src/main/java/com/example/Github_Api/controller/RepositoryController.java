package com.example.Github_Api.controller;

import com.example.Github_Api.GithubApiApplication;
import com.example.Github_Api.client.GithubClient;
import com.example.Github_Api.model.GithubRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepositoryController {
    private GithubClient githubClient;

    public RepositoryController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping("/get")
    public List<GithubRepository> getRepos(@RequestParam String user) {
        return githubClient.getRepositories(user);
    }


}
