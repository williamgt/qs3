package no.ntnu.idatt2105.gr13.qs3backend.service.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.person.Person;
import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserRole;
import no.ntnu.idatt2105.gr13.qs3backend.repository.security.JdbcAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JdbcAuthRepository repository;

    public Person login(User user){

        return null;
    }

    public Role getRole(User user){
        return repository.getUserWithRole(user);
    };
}
