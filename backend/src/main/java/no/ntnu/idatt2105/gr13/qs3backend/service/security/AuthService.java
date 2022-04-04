package no.ntnu.idatt2105.gr13.qs3backend.service.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserLogin;
import no.ntnu.idatt2105.gr13.qs3backend.repository.user.JdbcUserRepository;
import no.ntnu.idatt2105.gr13.qs3backend.repository.security.JdbcAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for AuthController
 */
@Service
public class AuthService {
    @Autowired
    JdbcAuthRepository repository;
    @Autowired
    JdbcUserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Method for logging in user. Returns true/false based on correct info
     * Sets password of user to an encoded password to match the one in the database
     * @param user
     * @return
     */
    public User login(UserLogin user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.getUserDetails(user);
    }

    /**
     * Returns role of user. Also makes sure password is correct before returning role
     * Used for auth
     * @param user
     * @return
     */
    public Role getRole(User user){
        return repository.getRoleOfUser(user);
    };

    /**
     * Returns role of user. Does not make sure password is correct before returning role
     * @param user
     * @return
     */
    public Role getRoleNotProt(User user){
        return repository.getRoleOfUser(user);
    }

    /**
     * Used for authenticating user. Uses repo.authUser which returns encoded password
     * If passwords match return encoded password, if not throw exception
     * @param user
     * @return
     */
    public String authUser(UserLogin user){
        UserLogin psw = repository.authUser(user);
        if(!passwordEncoder.matches(user.getPassword(), psw.getPassword()))
            throw new BadCredentialsException("Wrong username or password");
        return psw.getPassword();
    };
}
