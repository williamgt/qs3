package no.ntnu.idatt2105.gr13.qs3backend.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        // get user and password info from the request
        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();
        no.ntnu.idatt2105.gr13.qs3backend.model.user.User user = new no.ntnu.idatt2105.gr13.qs3backend.model.user.User(email, password);
        // check credentials
        // NOTE: in a real-world scenario, one should do a database lookup for valid credentials instead of hardcoded check as here!
        //if()
        if (email.equals("admin") && password.equals("password")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("USER"));
            final UserDetails principal = new User(email, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
