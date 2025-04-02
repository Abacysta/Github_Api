package exception;


public class GithubUserNotFoundException extends RuntimeException {
    public GithubUserNotFoundException(String username) {
        super("User " + username + " not found.");
    }
}
