package com.test.antipn.web;


import com.test.antipn.dto.UserDto;
import com.test.antipn.model.User;
import com.test.antipn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;


@Controller

public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/registration")
    public String registrationPage(Model model) {
        System.out.println("registration page get method");
        return "index.html";
    }

    @RequestMapping(value = "/registration/save", method = RequestMethod.POST)
    public String doRegistration(@ModelAttribute("userDto") UserDto userDto) {
        System.out.println("registration page post method");
        System.out.println("Controller - login =" + userDto.getLogin() + " phone =" + userDto.getPhone() + " email =" + userDto.getEmail() + " password =" + userDto.getPassword());
        authService.signUp(new User(userDto.getLogin(), userDto.getEmail(), userDto.getPhone(), userDto.getPassword().substring(0, userDto.getPassword().indexOf(","))));
        return "redirect:/registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showingLoginPage() {
        System.out.println("login page get method");
        return "html/login.html";
    }

    @PostMapping(value = "/login")
    public String loginProcces(@RequestParam("login") String login, @RequestParam("password") String password) {
        User user = authService.searchByLoginAndPass(login, password);
        if (user != null) {
            System.out.println("Successfully authorised user");
        } else {
            System.out.println("Authorization failed");
        }
        return "redirect:/login";
    }


}
