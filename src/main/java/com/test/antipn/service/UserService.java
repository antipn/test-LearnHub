package com.test.antipn.service;

import com.test.antipn.model.User;
import com.test.antipn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User searchByLogin(String login) {
        return userRepository.getUserByName(login);
    }

    public User signUp(User user) {

        //checking for clone username
        if (userRepository.getUserByName(user.getName()) != null) {
            throw new RuntimeException("User with name " + user.getName() + " already exits in DB");
        }
        //checking email
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User with such email " + user.getEmail() + " already exits in DB");
        }
        if (userRepository.getUserByPhone(user.getPhone()) != null) {
            throw new RuntimeException("User with such phone number " + user.getEmail() + " already exits in DB");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(username);
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), Collections.emptyList());
    }
}
