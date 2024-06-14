package PochingLao.dto;

// Define a record representing credentials with login and password fields
public record CredentialsDto(String login, char[] password) {
    public String getLogin() {
        return login;
    }
    public char[] getPassword() {
        return password;
    }
}
