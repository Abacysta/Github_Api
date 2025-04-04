# GitHub API

Simple Spring Boot application that serves as an API to check public repositories for given GitHub usernames.

The application exposes a single endpoint. After you provide a username, it queries GitHub and returns a list of non-forked repositories along with their branches and last commit information.

---

## Technologies

- Java 21  
- Spring Boot 3.4.4  
- Spring Web  
- WireMock  
- Lombok  

---

## Example

For the endpoint:
[GET /api/github/Abacysta](http://localhost:8080/api/github/Abacysta)

One of the returned repositories will look like:

```json
[
  {
    "repositoryName": "react_express",
    "ownerLogin": "Abacysta",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "1cafa6fc0de2073c2826c45cf6467d54b785a3b4"
      }
    ]
  }
]
