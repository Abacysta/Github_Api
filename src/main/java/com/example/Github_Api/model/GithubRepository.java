package com.example.Github_Api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubRepository {
    private String name;
    @JsonProperty("owner")
    private GithubOwner owner;
    @JsonProperty("fork")
    private boolean isFork;
}
