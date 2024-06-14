package PochingLao.dto;

public record SignUpDto(String firstName, String lastName, String login, char[] password) {
    // Accessor method for login
    public String getLogin() {
        return login;
    }

    // Accessor method for password
    public char[] getPassword() {
        return password;
    }
}
