package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class UserPerson {
    private String email;
    private String firstname;
    private String lastname;
    private int id;

    public UserPerson(String email, String firstName, String lastName, int id) {
        this.email = email;
        this.firstname = firstName;
        this.lastname = lastName;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }


    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public int getId() {
        return id;
    }
}
