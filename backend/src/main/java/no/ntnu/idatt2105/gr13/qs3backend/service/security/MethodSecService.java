package no.ntnu.idatt2105.gr13.qs3backend.service.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPerson;
import no.ntnu.idatt2105.gr13.qs3backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Service for getting currently logged in user for method isTargetUser
 */
@Service
public class MethodSecService {

    @Autowired
    UserService userService;

    /**
     * Checks if userId matches the logged in users ID
     * @param id
     * @return True/false
     */
    public boolean isTargetUser(long id) {
        String emailAuth = getCurrentAuthEmail();
        UserPerson user = userService.findById(id);
        return(emailAuth != null && !emailAuth.isEmpty() && (Objects.equals(user.getEmail(), emailAuth)));
    }

    /**
     * Returns current logged-in users email
     * @return
     */
    private String getCurrentAuthEmail(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(Optional.ofNullable(auth).isPresent())
            return (String) auth.getPrincipal();

        return null;
    }
}
