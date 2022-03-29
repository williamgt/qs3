package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserDB {
    private String email;
    private String password; //TODO need to look close on how to handle pswd
    private int id;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
