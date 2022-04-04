package no.ntnu.idatt2105.gr13.qs3backend.repository.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserLogin;
import org.springframework.stereotype.Repository;

/**
 * The interface for the Auth repository.
 */
@Repository
public interface AuthRepository {
    /**
     * Gets user with role.
     *
     * @param user the user
     * @return the user with role
     */
    Role getUserWithRole(User user);

    /**
     * Gets role of user.
     *
     * @param user the user
     * @return the role of user
     */
    Role getRoleOfUser(User user);

    /**
     * Authorizes user that logs in.
     *
     * @param user the user
     * @return the user login
     */
    UserLogin authUser(UserLogin user);
}
