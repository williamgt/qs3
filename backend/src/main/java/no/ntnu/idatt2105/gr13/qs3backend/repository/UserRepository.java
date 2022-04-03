package no.ntnu.idatt2105.gr13.qs3backend.repository;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.AdminUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.StudentUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.UserBasic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository {

    User getUserDetails(UserLogin user);

    List<User> findAllDetails();

    int getID(String email);

    List<UserProtected> findAll();
    UserPerson findById(long id);

    Boolean isUser(String email);

    User findByIdAdmin(long id);


    @Transactional
    int registerUser(User user);

    @Transactional
    int makeTA(int taID);

    @Transactional
    int makeTeacher(int teacherID);

    @Transactional
    int makeStudent(int studentID);

    @Transactional
    int makeAdmin(int adminID);


    Boolean updateUser(User user);
    Boolean deleteUser(int id);
}
