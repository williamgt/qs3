package no.ntnu.idatt2105.gr13.qs3backend.model.user.basics;

public class UserBasicWithId extends UserBasic {
    private int id;

    public UserBasicWithId(String firstname, String lastname, String email, int id) {
        super(firstname, lastname, email);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
