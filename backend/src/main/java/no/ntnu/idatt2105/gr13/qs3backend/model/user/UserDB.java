package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserDB extends User{

    private int id;

    public UserDB(UserDB user) {
        super(new User(user.getEmail(), user.getPassword()));
        id = user.getId();
    }

    public UserDB(int id) {
        this.id = id;
    }

    public UserDB(String email, String password, int id) {
        super(email, password);
        this.id = id;
    }

    public UserDB(User user, int id) {
        super(user);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
