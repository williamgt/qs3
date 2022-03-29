package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class User {
    private String username;
    private String email;
    private String password; //TODO need to look close on how to handle pswd

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
