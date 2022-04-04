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
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        this.id = id;
    }

    public UserDB(String email, int id) {
        super(email);
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
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
