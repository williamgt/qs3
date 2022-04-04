package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a user including it's role.
 */
public class UserRoleString {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;

    /**
     * Instantiates a new User role string.
     *
     * @param email     the email
     * @param password  the password
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param role      the role
     */
    public UserRoleString( String email, String password, String firstname, String lastname, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email.trim();
        this.password = password;
        this.role = role.toUpperCase();
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
