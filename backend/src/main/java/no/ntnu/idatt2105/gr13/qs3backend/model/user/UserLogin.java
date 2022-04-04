package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a login request from the frontend.
 */
public class UserLogin {
    private String email;
    private String password;

    /**
     * Instantiates a new User login.
     */
    public UserLogin() {
    }

    /**
     * Instantiates a new User login.
     *
     * @param email    the email
     * @param password the password
     */
    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Instantiates a new User login.
     *
     * @param email the email
     */
    public UserLogin(String email) {
        this.email = email;
    }

    /**
     * Instantiates a new User login.
     *
     * @param user the user
     */
    public UserLogin(User user) {
        email = user.getEmail();
        password = user.getPassword();
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
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
