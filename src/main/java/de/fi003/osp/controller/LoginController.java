package de.fi003.osp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("pageTitle","Login - Application");
        return "login";
    }

    @GetMapping("reset")
    public String restPassword(Model model) {
        model.addAttribute("pageTitle","Password vergessen - Application");
        return "forget_password";
    }
}
