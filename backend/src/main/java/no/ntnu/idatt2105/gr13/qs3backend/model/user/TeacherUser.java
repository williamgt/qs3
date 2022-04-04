package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a teacher user.
 */
public class TeacherUser extends User {
    /**
     * Instantiates a new Teacher user.
     *
     * @param email     the email
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     */
    public TeacherUser(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }
}
