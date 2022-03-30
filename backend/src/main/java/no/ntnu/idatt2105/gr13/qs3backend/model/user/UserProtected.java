package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserProtected {
    private String email;
    private int id;

    public UserProtected(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
