package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User findByUsername(String username);
    List<User> findAll();
    UserDB findById(long id);
    Boolean isAdmin(User user);
    Boolean isTA(User user);
    Boolean isTeacher(User user);
}
