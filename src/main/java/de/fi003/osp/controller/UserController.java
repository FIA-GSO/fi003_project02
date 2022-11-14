package de.fi003.osp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{username}")
    public String main(Model model, @PathVariable String username) {
        model.addAttribute("pageTitle","Login - Application");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody String user){
        return user;

    }

    @PostMapping("/register")
    public String register(@RequestBody String user){
        return user;

    }
}
