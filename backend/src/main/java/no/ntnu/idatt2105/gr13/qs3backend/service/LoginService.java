package no.ntnu.idatt2105.gr13.qs3backend.service;


import no.ntnu.idatt2105.gr13.qs3backend.model.user.User;
import no.ntnu.idatt2105.gr13.qs3backend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public User okLogin(String username){
        return loginRepository.findByUsername(username);
    }

    public boolean login(User login){
        User _login = loginRepository.findByUsername(login.getUsername());
        if(_login == null || !login.getPassword().equals(_login.getPassword())) {
            return false;
        }
        return true;
    }

    public boolean checkLogin(User login){
        User _login = loginRepository.findByUsername(login.getUsername());
        return _login != null && login.getPassword().equals(_login.getPassword());
    }

}
