package no.ntnu.idatt2105.gr13.qs3backend.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    AuthService service;

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        // get user and password info from the request
        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();

        //NEED TO CHECK IF STUDENT, TA, TEACHER, ADMIN
        Role role = service.getRole(new no.ntnu.idatt2105.gr13.qs3backend.model.user.User(email, password));
        switch (role){
            case STUDENT:
                System.out.println("USER");
                return authPerson(email, password, "USER");
            case TA:
                return authPerson(email, password, "TA");
            case TEACHER:
                return authPerson(email, password, "TEACHER");
            case ADMIN:
                System.out.println("ADMIN");
                return authPerson(email, password, "ADMIN");
            default:
                return null;
        }
    }

    private Authentication authPerson(String email, String password, String role){
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(role));
        final UserDetails principal = new User(email, password, grantedAuths);
        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        return auth;
    }



    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
