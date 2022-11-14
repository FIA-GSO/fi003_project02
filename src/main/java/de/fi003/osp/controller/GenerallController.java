package de.fi003.osp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class GenerallController {

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("pageTitle"," - Application");
        return "login";
    }

}
