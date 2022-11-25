package com.test.antipn.service;

import com.test.antipn.model.User;
import com.test.antipn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User searchByLogin(String login) {
        return userRepository.getUserByName(login);
    }

    public User searchByLoginAndPass(String login, String password) {
        User userInDb = userRepository.getUserByName(login);
        if (userInDb != null) {
            return (userInDb.getPassword().equals(password)) ? userInDb : null;
        } else {
            return null;
        }
//        final User byLogin = userRepository.getUserByName(login);
//        return (byLogin.getPassword().equals(password)) ? byLogin : null;
    }

    public User signUp(User user) {
        System.out.println("Service - checking user in DB and saving user");
        if (userRepository.getUserByName(user.getName()) == null) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User with name " + user.getName() + "already exits in DB");
        }


    }
}
