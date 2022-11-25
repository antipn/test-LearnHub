package com.test.antipn.security;

import com.test.antipn.model.User;
import com.test.antipn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Received credentials: " + authentication.getName() + " " + authentication.getCredentials().toString());

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        final User targetUser = userService.searchByLogin(name);

        if (targetUser == null) {
            System.out.println("Unknown user");
            throw new BadCredentialsException("Unknown user " + name);
        }
        if (!password.equals(targetUser.getPassword())) {
            System.out.println("Bad password");
            throw new BadCredentialsException("Bad password");
        }
        return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        //return true;
    }
}
