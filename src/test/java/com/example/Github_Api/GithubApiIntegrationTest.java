package com.example.Github_Api;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class GithubApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesForExistingUser() {
        String user = "Abacysta";
        String githubReposUrl = "/users/" + user + "/repos";
        String githubBranchesUrl = "/repos/" + user + "/react_express/branches";

        stubFor(get(urlEqualTo(githubReposUrl))
                .willReturn(okJson("""
                    [
                        {
                            "name": "react_express",
                            "fork": false,
                            "owner": { "login": "Abacysta" }
                        }
                    ]
                """)));

        stubFor(get(urlEqualTo(githubBranchesUrl))
                .willReturn(okJson("""
                    [
                        {
                            "name": "main",
                            "commit": { "sha": "1cafa6fc0de2073c2826c45cf6467d54b785a3b4" }
                        }
                    ]
                """)));

        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + user, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("react_express", "Abacysta", "main", "1cafa6fc0de2073c2826c45cf6467d54b785a3b4");
    }

    @Test
    void shouldReturnErrorForNonExistingUser() {
        String user = "Abacystaa";
        String githubReposUrl = "/users/" + user + "/repos";

        stubFor(get(urlEqualTo(githubReposUrl))
                .willReturn(aResponse().withStatus(404)));

        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + user, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("404");
        assertThat(response.getBody()).contains("User "  + user +" not found.");
    }


    @Test
    void shouldReturnNullListForExistingUserWithNoneRepositories() {
        String user = "musmike";
        String githubReposUrl = "/users/" + user + "/repos";

        stubFor(get(urlEqualTo(githubReposUrl))
                .willReturn(okJson("[]")));

        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + user, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("[]");
    }
}
