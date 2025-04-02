package com.example.Github_Api.model;


import java.util.List;

public class GithubRepoResponse {
    private String name;
    private String ownerLogin;
    private List<GithubBranch> branches;

    public GithubRepoResponse(String name, String ownerLogin, List<GithubBranch> branches) {
        this.name = name;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<GithubBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<GithubBranch> branches) {
        this.branches = branches;
    }
}
