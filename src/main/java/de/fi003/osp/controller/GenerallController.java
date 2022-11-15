package de.fi003.osp.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.fi003.osp.entity.Course;
import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.ClassRepository;
import de.fi003.osp.repository.CourseRepository;
import de.fi003.osp.repository.TeacherRepository;
import de.fi003.osp.utils.Helper;

@Controller
@RequestMapping("")
public class GenerallController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("pageTitle"," - Application");
        return Helper.checkLogin(teacherRepository, "redirect:/class");
    }

    @GetMapping("/class")
    public String getClassOverview(Model model) {
        model.addAttribute("pageTitle","Klassenübersicht - Application");
        ArrayList<de.fi003.osp.entity.Class> classList = classRepository.findAll();
        model.addAttribute("list", classList);
        return Helper.checkLogin(teacherRepository, "class_select");
    }

    @GetMapping("/{className}/grades")
    public String getClassGrades(Model model, @PathVariable String className){
        model.addAttribute("pageTitle","Notenübersicht - Application");
        Optional<de.fi003.osp.entity.Class> optClass = classRepository.findByName(className);
        if(!optClass.isPresent()){
            return "404";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Teacher> user = teacherRepository.findUserByEmail(auth.getName());
        ArrayList<Course> courseList = courseRepository.findAllByTeacherId(user.get().getId());
        model.addAttribute("courses", courseList);
        model.addAttribute("class", optClass);
        return Helper.checkLogin(teacherRepository, "grade_entries");
    }

    @GetMapping("/{className}/weekly")
    public String getClassWekly(Model model, @PathVariable String className){
        model.addAttribute("pageTitle","Wochenübersicht - Application");
        Optional<de.fi003.osp.entity.Class> optClass = classRepository.findByName(className);
        if(!optClass.isPresent()){
            return "404";
        }
        model.addAttribute("class", optClass);
        return Helper.checkLogin(teacherRepository, "calender_weekly_entries");
    }
}
