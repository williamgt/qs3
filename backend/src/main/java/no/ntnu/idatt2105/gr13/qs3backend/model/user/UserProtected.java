package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserProtected {
    private String email;
    private String firstName;
    private String lastName;

    public UserProtected(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


}
