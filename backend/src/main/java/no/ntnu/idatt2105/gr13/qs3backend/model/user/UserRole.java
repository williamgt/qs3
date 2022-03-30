package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;

public class UserRole extends UserDB{

    private final Role role;

    public UserRole(UserDB user, Role role) {
        super(new UserDB(user.getEmail(), user.getPassword(), user.getId()));
        this.role = role;
    }

    public Role getRole() {
        return role;
    }


}
