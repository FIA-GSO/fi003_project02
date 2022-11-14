package de.fi003.osp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{username}")
    public String main(@PathVariable String username) {
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
