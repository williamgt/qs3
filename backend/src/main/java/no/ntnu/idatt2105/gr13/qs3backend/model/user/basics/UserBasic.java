package no.ntnu.idatt2105.gr13.qs3backend.model.user.basics;

/**
 * Models a user containing basic information such as the users first and last name, including email.
 */
public class UserBasic {
    private String firstname;
    private String lastname;
    private String email;

    /**
     * Instantiates a new User basic.
     */
    public UserBasic() {
    }

    /**
     * Instantiates a new User basic.
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param email     the email
     */
    public UserBasic(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
