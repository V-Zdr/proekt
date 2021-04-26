package mk.finki.ukim.proekt.service;

import mk.finki.ukim.proekt.model.Role;
import mk.finki.ukim.proekt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password,String repeatPassword, Role role);
}
