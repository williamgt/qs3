package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a response to frontend in the form of a protected user. No password is set here.
 */
public class UserProtected {
    private String email;
    private String firstName;
    private String lastName;

    /**
     * Instantiates a new User protected.
     *
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     */
    public UserProtected(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }


}
