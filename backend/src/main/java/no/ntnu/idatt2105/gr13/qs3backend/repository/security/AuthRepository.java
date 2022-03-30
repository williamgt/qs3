package no.ntnu.idatt2105.gr13.qs3backend.repository.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository {
    UserRole getUserWithRole(User user);

}
