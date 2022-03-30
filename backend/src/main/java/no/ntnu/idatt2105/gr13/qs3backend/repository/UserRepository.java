package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User findByUsername(String username);
    List<UserPersonAll> findAllDetails();
    List<UserProtected> findAll();
    UserPerson findById(long id);

    UserPerson findByIdAdmin(long id);
}
