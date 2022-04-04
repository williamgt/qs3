package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password; //TODO need to look close on how to handle pswd
    private int id;

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

    public User(String email, String password, String firstName, String lastName) {
        check(email, password, firstName, lastName);
        this.email = email;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
    }

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

    public User(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }
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
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(!mat.matches() || !(email.contains(".no") || email.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }
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
