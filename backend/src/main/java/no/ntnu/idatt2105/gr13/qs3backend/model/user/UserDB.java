package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a user from the DB containing a user ID.
 */
public class UserDB extends User{
    private int id;

    /**
     * Instantiates a new User db.
     *
     * @param user the user
     */
    public UserDB(UserDB user) {
        super(new User(user.getEmail(), user.getPassword()));
        id = user.getId();
    }

    /**
     * Instantiates a new User db.
     *
     * @param id the id
     */
    public UserDB(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new User db.
     *
     * @param email    the email
     * @param password the password
     * @param id       the id
     */
    public UserDB(String email, String password, int id) {
        super(email, password);
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        this.id = id;
    }

    /**
     * Instantiates a new User db.
     *
     * @param email the email
     * @param id    the id
     */
    public UserDB(String email, int id) {
        super(email);
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        this.id = id;
    }

    /**
     * Instantiates a new User db.
     *
     * @param user the user
     * @param id   the id
     */
    public UserDB(User user, int id) {
        super(user);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
