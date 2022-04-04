package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserRoleString {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;

    public UserRoleString( String email, String password, String firstname, String lastname, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email.trim();
        this.password = password;
        this.role = role.toUpperCase();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
