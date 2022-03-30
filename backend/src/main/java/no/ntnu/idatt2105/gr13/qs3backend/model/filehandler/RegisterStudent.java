package no.ntnu.idatt2105.gr13.qs3backend.model.filehandler;

public class RegisterStudent {

    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public RegisterStudent(String lastname, String firstname, String email, String password) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
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
}
