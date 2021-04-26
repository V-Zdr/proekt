package mk.finki.ukim.proekt.service.impl;

import mk.finki.ukim.proekt.model.Role;
import mk.finki.ukim.proekt.model.User;
import mk.finki.ukim.proekt.model.exceptions.InvalidUsernameException;
import mk.finki.ukim.proekt.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.proekt.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.proekt.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.proekt.repository.UserRepository;
import mk.finki.ukim.proekt.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword,Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(InvalidUsernameException::new);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));
        return userDetails;
    }
}
