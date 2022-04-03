package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserLogin {
    private String email;
    private String password; //TODO need to look close on how to handle pswd

    public UserLogin() {
    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLogin(String email) {
        this.email = email;
    }

    public UserLogin(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
