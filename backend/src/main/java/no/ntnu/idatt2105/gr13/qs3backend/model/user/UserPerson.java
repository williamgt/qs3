package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a user with some personal information such as first and last name
 */
public class UserPerson {
    private String email;
    private String firstname;
    private String lastname;
    private int id;

    /**
     * Instantiates a new User person.
     *
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     * @param id        the id
     */
    public UserPerson(String email, String firstName, String lastName, int id) {
        this.email = email;
        this.firstname = firstName;
        this.lastname = lastName;
        this.id = id;
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
