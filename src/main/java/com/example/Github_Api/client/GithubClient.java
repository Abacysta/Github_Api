package com.example.Github_Api.client;

import com.example.Github_Api.model.GithubBranch;
import com.example.Github_Api.model.GithubRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class GithubClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final  String GITHUB_API_URL = "https://api.github.com";

    // example: https://api.github.com/users/octocat/repos
    public List<GithubRepository> getRepositories(String username) {
        String url = GITHUB_API_URL + "/users/" + username + "/repos";

        try{
            GithubRepository[] repos = restTemplate.getForObject(url, GithubRepository[].class);

            if(repos == null) return null;

            ArrayList<GithubRepository> filteredRepos = (ArrayList<GithubRepository>) Arrays.stream(repos)
                    .filter(repo -> !repo.isFork()) // we dont care about forks
                    .collect(Collectors.toList());
            return filteredRepos;
        } catch (Exception e) {
            return null;
        }
    }
    public List<GithubBranch> getBranches(String owner, String repoName) {
        String url = GITHUB_API_URL + "/repos/" + owner + "/" + repoName + "/branches";
        GithubBranch[] branches = restTemplate.getForObject(url, GithubBranch[].class);
        if(branches == null) return null;
        return Arrays.asList(branches);
    }
}
