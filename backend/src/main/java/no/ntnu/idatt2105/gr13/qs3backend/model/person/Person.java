package no.ntnu.idatt2105.gr13.qs3backend.model.person;

public class Person {
    String firstname;
    String lastname;

    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstname = firstName;
        this.lastname = surName;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstname + '\'' +
                ", surName='" + lastname + '\'' +
                '}';
    }
}
