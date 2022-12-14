package com.test.antipn.web;

import com.test.antipn.dto.UserDto;
import com.test.antipn.model.User;
import com.test.antipn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/registration")
    public String registrationPage(Model model) {
        return "index.html";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(auth);

        ModelAndView model = new ModelAndView();
        model.setViewName("html/welcome.html");
        model.addObject("login", name);

        System.out.println(name);

        return model;
        //return "html/welcome.html";
    }

    @RequestMapping(value = "/registration/save", method = RequestMethod.POST)
    public String doRegistration(@ModelAttribute("userDto") UserDto userDto) {

        //comparing passwords
        String userPasswordLeftPart = userDto.getPassword().substring(0, userDto.getPassword().indexOf(","));
        String userPasswordRightPart = userDto.getPassword().substring(userDto.getPassword().indexOf(",") + 1);

        if (userPasswordRightPart.equals(userPasswordLeftPart)) { //if pass1 field == pass2 field
            userService.signUp(new User(userDto.getLogin().trim(), userDto.getEmail().trim(), userDto.getPhone().trim(), userPasswordLeftPart));
            return "redirect:/login";
        }
        return "redirect:/registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showingLoginPage() {
        return "html/login.html";
    }
}
