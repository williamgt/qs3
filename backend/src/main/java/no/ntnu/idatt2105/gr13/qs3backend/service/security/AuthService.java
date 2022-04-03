package no.ntnu.idatt2105.gr13.qs3backend.service.security;

import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserLogin;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserRole;
import no.ntnu.idatt2105.gr13.qs3backend.repository.JdbcUserRepository;
import no.ntnu.idatt2105.gr13.qs3backend.repository.security.JdbcAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JdbcAuthRepository repository;
    @Autowired
    JdbcUserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User login(UserLogin user){
        System.out.println(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.getUserDetails(user);
    }

    public Role getRole(User user){
        return repository.getRoleOfUser(user);
    };

    public Role getRoleNotProt(User user){
        return repository.getRoleOfUser(user);
    }

    public String authUser(UserLogin user){
        UserLogin psw = repository.authUser(user);
        if(!passwordEncoder.matches(user.getPassword(), psw.getPassword()))
            throw new BadCredentialsException("Wrong username or password");
        return psw.getPassword();
    };
}
