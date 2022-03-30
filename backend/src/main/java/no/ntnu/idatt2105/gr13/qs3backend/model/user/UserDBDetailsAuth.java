package no.ntnu.idatt2105.gr13.qs3backend.model.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserDBDetailsAuth extends UserDB{
    private Collection<? extends GrantedAuthority> authorities;


    public UserDBDetailsAuth(String email, String password, int id, List<GrantedAuthority> authorities) {
        super(email, password, id);
        this.authorities = authorities;
    }
}
