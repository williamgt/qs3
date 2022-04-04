package no.ntnu.idatt2105.gr13.qs3backend.model.user.basics;

/**
 * Models a user containing basic information listen in UserBasic, but also the user's ID.
 */
public class UserBasicWithId extends UserBasic {
    private int id;

    /**
     * Instantiates a new User basic with id.
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param email     the email
     * @param id        the id
     */
    public UserBasicWithId(String firstname, String lastname, String email, int id) {
        super(firstname, lastname, email);
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }
}
