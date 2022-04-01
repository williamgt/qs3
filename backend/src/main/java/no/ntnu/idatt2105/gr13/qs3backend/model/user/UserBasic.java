package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserBasic {
    private String firstname;
    private String lastname;
    private String email;

    public UserBasic() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
