package com.example.Github_Api.client;

import com.example.Github_Api.model.GithubBranch;
import com.example.Github_Api.model.GithubRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GithubClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API_URL = "https://api.github.com";

    public List<GithubRepository> getRepositories(String username) {
        String url = GITHUB_API_URL + "/users/" + username + "/repos";

        try {
            ResponseEntity<GithubRepository[]> response = restTemplate.getForEntity(url, GithubRepository[].class);

            if (response.getBody() == null) {
                throw new exception.GithubUserNotFoundException(username);
            }

            return Arrays.stream(response.getBody())
                    .filter(repo -> !repo.isFork())
                    .collect(Collectors.toList());

        } catch (HttpClientErrorException.NotFound e) {
            throw new exception.GithubUserNotFoundException(username);
        }
    }

    public List<GithubBranch> getBranches(String owner, String repoName) {
        String url = GITHUB_API_URL + "/repos/" + owner + "/" + repoName + "/branches";

        GithubBranch[] branches = restTemplate.getForObject(url, GithubBranch[].class);
        return branches != null ? Arrays.asList(branches) : List.of();
    }
}
