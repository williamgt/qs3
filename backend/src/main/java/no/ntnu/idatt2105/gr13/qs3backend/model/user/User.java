package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class User {
    private String email;
    private String password; //TODO need to look close on how to handle pswd
    private String firstName;
    private String lastName;
    private int id;

    public User(String email, String password, String firstName, String lastName, int id) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String firstName, String lastName, int id) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
