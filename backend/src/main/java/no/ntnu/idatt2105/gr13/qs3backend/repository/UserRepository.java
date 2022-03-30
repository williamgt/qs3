package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserDB;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserPerson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User findByUsername(String username);
    List<User> findAll();
    UserDB findById(long id);
    List<UserPerson> findAll();
    UserPerson findById(long id);

    UserPerson findByIdAdmin(long id);
}
