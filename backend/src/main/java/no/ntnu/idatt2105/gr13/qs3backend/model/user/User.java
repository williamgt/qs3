package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password; //TODO need to look close on how to handle pswd
    private int id;

    public User(String email, String password, String firstName, String lastName, int id) {
        this.email = email;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
        this.id = id;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public User(String email,String firstname, String lastname, int id) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }

    public User(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
