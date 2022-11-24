package com.test.antipn.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class AuthController {

    @RequestMapping("/")
    public ModelAndView registering() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        System.out.println("we are here");
        return modelAndView;
    }

    @RequestMapping("/login.html")
    public ModelAndView loginIn() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/login.html");
        System.out.println("we are here2");
        return modelAndView;
    }

}
