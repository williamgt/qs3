package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Models a user with name, email, password and ID of user in DB.
 */
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int id;

    /**
     * Helper method to check validity of passed arguments. Throws IllegalArgumentException if any fields are empty or
     * null or if email has invalid pattern.
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @return
     */
    private boolean check(String email, String password, String firstName, String lastName){
        if(email == null){
            throw new IllegalArgumentException("Email can't be null");
        }
        if(password == null || password.equals("")){
            throw new IllegalArgumentException("Password can't be null or empty");
        }
        if(firstName == null || firstName.equals("")){
            throw new IllegalArgumentException("firstName can't be null or empty");
        }
        if(lastName == null || lastName.equals("")){
            throw new IllegalArgumentException("lastName can't be null or empty");
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }

        return true;
    }

    /**
     * Instantiates a new User. Throws IllegalArgumentException if id is less than 0.
     *
     * @param email     the email
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param id        the id
     */
    public User(String email, String password, String firstName, String lastName, int id) {
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        check(email, password, firstName, lastName);
        this.email = email;
        this.password = password;
        this.firstname = firstName.trim();
        this.lastname = lastName.trim();
        this.id = id;
    }

    /**
     * Instantiates a new User.
     *
     * @param email     the email
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     */
    public User(String email, String password, String firstName, String lastName) {
        check(email, password, firstName, lastName);
        this.email = email;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
    }

    /**
     * Instantiates a new User. Throws IllegalArgumentException if String values are empty or
     * if email is on an invalid form.
     *
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     */
    public User(String email, String firstName, String lastName) {
        if(firstName == null || firstName.equals("")){
            throw new IllegalArgumentException("firstName can't be null or empty");
        }
        if(lastName == null || lastName.equals("")){
            throw new IllegalArgumentException("lastName can't be null or empty");
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }

        this.email = email;
        this.firstname = firstName;
        this.lastname = lastName;
    }

    /**
     * Instantiates a new User. Throws IllegalArgumentException if id is less than 0 or String values are empty or
     * if email is on an invalid form.
     *
     * @param email     the email
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param id        the id
     */
    public User(String email,String firstname, String lastname, int id) {
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        if(firstname == null || firstname.equals("")){
            throw new IllegalArgumentException("firstName can't be null or empty");
        }
        if(lastname == null || lastname.equals("")){
            throw new IllegalArgumentException("lastName can't be null or empty");
        }
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    /**
     * Instantiates a new User. Throws IllegalArgumentException if email pattern is invalid or password is empty.
     *
     * @param email    the email
     * @param password the password
     */
    public User(String email, String password) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }
        if(password == null || password.equals("")){
            throw new IllegalArgumentException("Password can't be null or empty");
        }
        this.email = email;
        this.password = password;
    }

    /**
     * Instantiates a new User. Throws IllegalArgumentException if email pattern is invalid.
     *
     * @param email the email
     */
    public User(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }
        this.email = email;
    }

    /**
     * Instantiates a new User.
     *
     * @param user the user
     */
    public User(User user) {
        email = user.getEmail();
        password = user.getPassword();
    }

    /**
     * Instantiates a new User.
     */
    public User() {
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
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
