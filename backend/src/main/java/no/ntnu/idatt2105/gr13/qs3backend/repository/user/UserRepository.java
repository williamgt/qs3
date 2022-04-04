package no.ntnu.idatt2105.gr13.qs3backend.repository.user;

import no.ntnu.idatt2105.gr13.qs3backend.model.user.*;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.AdminUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.StudentUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.UserBasic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface for the User repository.
 */
@Repository
public interface UserRepository {

    /**
     * Gets user details of a user.
     *
     * @param user the user
     * @return the user details
     */
    User getUserDetails(UserLogin user);

    /**
     * Gets all users and their details.
     *
     * @return the list
     */
    List<User> findAllDetails();

    /**
     * Gets id.
     *
     * @param email the email
     * @return the id
     */
    int getID(String email);

    /**
     * Gets all users.
     *
     * @return the list
     */
    List<UserProtected> findAll();

    /**
     * Find user by id .
     *
     * @param id the id
     * @return the user person
     */
    UserPerson findById(long id);

    /**
     * Checks whether a user is connected to a mail or not.
     *
     * @param email the email
     * @return the boolean
     */
    Boolean isUser(String email);

    /**
     * Finds admin by id.
     *
     * @param id the id
     * @return the user
     */
    User findByIdAdmin(long id);

    /**
     * Register user with given information.
     *
     * @param user the user
     * @return the int
     */
    @Transactional
    int registerUser(User user);

    /**
     * Make user with id a teaching assistant.
     *
     * @param taID the ta id
     * @return the int
     */
    @Transactional
    int makeTA(int taID);

    /**
     * Make user with id a teacher.
     *
     * @param teacherID the teacher id
     * @return the int
     */
    @Transactional
    int makeTeacher(int teacherID);

    /**
     * Make user with id a student.
     *
     * @param studentID the student id
     * @return the int
     */
    @Transactional
    int makeStudent(int studentID);

    /**
     * Make user with id admin.
     *
     * @param adminID the admin id
     * @return the int
     */
    @Transactional
    int makeAdmin(int adminID);

    /**
     * Update user.
     *
     * @param user the user
     * @return the boolean
     */
    Boolean updateUser(User user);

    /**
     * Delete user.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean deleteUser(int id);
}
