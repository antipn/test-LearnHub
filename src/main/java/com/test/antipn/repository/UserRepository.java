package com.test.antipn.repository;

import com.test.antipn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByName(String login);

    public User getUserByEmail(String email);

    public User getUserByPhone(String phone);
}
