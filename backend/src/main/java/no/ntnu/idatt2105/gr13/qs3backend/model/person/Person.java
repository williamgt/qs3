package no.ntnu.idatt2105.gr13.qs3backend.model.person;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;

public class Person {
    private String firstName;
    private String lastName;
    private UserDB user;
    private Role role;

    public Person(String firstName, String lastName, UserDB user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public Person() {
    }

    public Person(String firstName, String lastName, UserDB user, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }
}
