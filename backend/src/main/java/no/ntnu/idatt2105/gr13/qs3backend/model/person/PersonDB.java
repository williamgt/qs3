package no.ntnu.idatt2105.gr13.qs3backend.model.person;

public class PersonDB {
    private String firstName;
    private String lastName;
    private int id;


    public PersonDB(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }
}
