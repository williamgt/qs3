package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a teaching assistant user.
 */
public class TAUser extends User {
    /**
     * Instantiates a new Ta user.
     *
     * @param email     the email
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     */
    public TAUser(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }
}
