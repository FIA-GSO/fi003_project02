package de.fi003.osp.controller;

import java.util.ArrayList;

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

    @GetMapping("/class")
    public String getClassOverview(Model model) {
        model.addAttribute("pageTitle"," Klassenübersicht - Application");
        ArrayList<String> classList = new ArrayList<>();
        classList.add("FI001");
        classList.add("FI002");
        classList.add("FI003");
        model.addAttribute("list", classList);
        return "class_select";
    }
}
