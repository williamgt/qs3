package no.ntnu.idatt2105.gr13.qs3backend.controller.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import no.ntnu.idatt2105.gr13.qs3backend.controller.UserController;
import no.ntnu.idatt2105.gr13.qs3backend.model.security.Role;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.UserLogin;
import no.ntnu.idatt2105.gr13.qs3backend.service.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
/**
 * Controller for Tokens
 * Taken from lecture example
 */
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(TokenController.class);
    @Autowired
    AuthService service;
    public static String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Method for generating token based on role of user
     * Authorizes user twice before returning token
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(@RequestParam("username") final String username, @RequestParam("password") final String password) throws Exception {
        try {
            String psw = service.authUser(new UserLogin(username, password));
            logger.info("User authorized once");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, psw));
            logger.info("User auth twice");
            Role role = service.getRole(new no.ntnu.idatt2105.gr13.qs3backend.model.user.User(username, password));
            logger.info(role.role);
            return generateToken1(username, role.role);
        } catch (BadCredentialsException e) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }
    }

    /**
     * Method for generating token based on user role
     * @param userId
     * @param role
     * @return
     * @throws Exception
     */
    public String generateToken1(String userId, String role) throws Exception {
        Key key = Keys.hmacShaKeyFor(keyStr.getBytes("UTF-8"));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userId", userId);
        claims.put("authorities", grantedAuthorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(key)
                .compact();
    }


}
