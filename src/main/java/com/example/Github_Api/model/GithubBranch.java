package com.example.Github_Api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubBranch {
    private String name;
    @JsonProperty("commit")
    private GithubCommit commit;
}
