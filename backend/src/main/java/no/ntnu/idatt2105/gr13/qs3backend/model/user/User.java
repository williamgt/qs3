package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class User {
    private String email;
    private String password; //TODO need to look close on how to handle pswd

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
