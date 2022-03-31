package no.ntnu.idatt2105.gr13.qs3backend.model.person;

public class Person {
    String firstname;
    String lastname;
    String email;

    public Person() {
    }

    public Person(String firstName, String surName, String email) {
        this.firstname = firstName;
        this.lastname = surName;
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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
