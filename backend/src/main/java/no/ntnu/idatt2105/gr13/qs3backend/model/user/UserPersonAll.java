package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserPersonAll extends UserPerson {
    private final String password;
    public UserPersonAll(String email, String firstName, String lastName, int id, String password) {
        super(email, firstName, lastName, id);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
