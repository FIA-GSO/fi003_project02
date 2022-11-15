package de.fi003.osp.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fi003.osp.repository.ClassRepository;

@Controller
@RequestMapping("")
public class GenerallController {

    @Autowired
    private ClassRepository classRepository;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("pageTitle"," - Application");
        return "login";
    }

    @GetMapping("/class")
    public String getClassOverview(Model model) {
        model.addAttribute("pageTitle"," Klassenübersicht - Application");
        ArrayList<de.fi003.osp.entity.Class> classList = classRepository.findAll();

        model.addAttribute("list", classList);
        return "class_select";
    }

    @GetMapping("/{className}/grades")
    public String getClassGrades(Model model, @PathVariable String className){
        model.addAttribute("pageTitle"," Notenübersicht - Application");
        Optional<de.fi003.osp.entity.Class> optClass = classRepository.findByName(className);
        if(!optClass.isPresent()){
            return "404";
        }
        model.addAttribute("class", optClass);
        return "grade_entries";
    }
}
