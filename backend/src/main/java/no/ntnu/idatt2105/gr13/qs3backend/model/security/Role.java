package no.ntnu.idatt2105.gr13.qs3backend.model.security;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    TEACHER("ROLE_TEACHER"),
    TA("ROLE_TA"),
    STUDENT("ROLE_STUDENT"),
    UNDEFINED("ROLE_UNDEFINED");

    public final String role;

    Role(String role) {
        this.role = role;
    }
}
