package com.test.antipn.web;


import com.test.antipn.dto.UserDto;
import com.test.antipn.model.User;
import com.test.antipn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
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
        userService.signUp(new User(userDto.getLogin(), userDto.getEmail(), userDto.getPhone(), userDto.getPassword().substring(0, userDto.getPassword().indexOf(","))));
        return "redirect:/registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showingLoginPage() {
        System.out.println("login page get method");
        return "html/login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(@RequestParam("login") String login, @RequestParam("password") String password) {
        System.out.println("login page post method");
        User user = userService.searchByLoginAndPass(login, password);
        if (user != null) {
            System.out.println("Successfully authorised user");
        } else {
            System.out.println("Authorization failed");
        }
        return "redirect:/registration";
    }

}
